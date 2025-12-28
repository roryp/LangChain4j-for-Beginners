package com.example.langchain4j.mcp;

import com.example.langchain4j.mcp.agents.AnalysisAgent;
import com.example.langchain4j.mcp.agents.FileAgent;
import com.example.langchain4j.mcp.agents.SummaryAgent;
import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.agentic.observability.AgentListener;
import dev.langchain4j.agentic.observability.AgentRequest;
import dev.langchain4j.agentic.observability.AgentResponse;
import dev.langchain4j.agentic.observability.AgentInvocationError;
import dev.langchain4j.agentic.scope.AgenticScope;
import dev.langchain4j.agentic.scope.ResultWithAgenticScope;
import dev.langchain4j.agentic.supervisor.SupervisorAgent;
import dev.langchain4j.agentic.supervisor.SupervisorAgentServiceImpl;
import dev.langchain4j.agentic.supervisor.SupervisorResponseStrategy;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openaiofficial.OpenAiOfficialChatModel;
import dev.langchain4j.service.tool.ToolProvider;

import java.time.Duration;
import java.util.List;

/**
 * Supervisor Agent Demo with AgenticScope.
 * 
 * Demonstrates:
 * - Supervisor pattern: LLM autonomously decides which agents to invoke
 * - AgenticScope: Shared context for agents to store/retrieve intermediate results
 * 
 * Agents store results via @Agent(outputKey="...") and access them through the scope.
 */
public class SupervisorAgentDemo {

    private static final String GITHUB_TOKEN = System.getenv("GITHUB_TOKEN");
    private static final String ALLOWED_DIRECTORY = System.getProperty("user.dir");

    public static void main(String[] args) throws Exception {
        validateEnvironment();

        try (McpClient mcpClient = createMcpClient()) {
            ChatModel model = createChatModel();
            ToolProvider mcpTools = McpToolProvider.builder()
                    .mcpClients(List.of(mcpClient))
                    .build();

            // Build agents
            FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
                    .chatModel(model)
                    .toolProvider(mcpTools)
                    .build();

            AnalysisAgent analysisAgent = AgenticServices.agentBuilder(AnalysisAgent.class)
                    .chatModel(model)
                    .build();

            SummaryAgent summaryAgent = AgenticServices.agentBuilder(SummaryAgent.class)
                    .chatModel(model)
                    .build();

            // Build supervisor that returns ResultWithAgenticScope to access shared context
            SupervisorAgent supervisor = ((SupervisorAgentServiceImpl<SupervisorAgent>) AgenticServices.supervisorBuilder())
                    .chatModel(model)
                    .subAgents(fileAgent, analysisAgent, summaryAgent)
                    .responseStrategy(SupervisorResponseStrategy.SUMMARY)
                    .listener(createAgentListener())
                    .build();

            // Invoke supervisor - the AgenticScope is automatically created and managed
            String filePath = ALLOWED_DIRECTORY + "/src/main/resources/file.txt";
            String request = "Read the file at " + filePath + " and analyze what it's about";

            System.out.println("Request: " + request);
            System.out.println("-".repeat(50));

            // ResultWithAgenticScope provides access to both the result and the shared scope
            ResultWithAgenticScope<String> result = supervisor.invokeWithAgenticScope(request);
            
            System.out.println("\nResponse:\n" + result.result());

            // Access agent outputs from the AgenticScope
            AgenticScope scope = result.agenticScope();
            System.out.println("\n--- Scope Contents ---");
            printScopeValue(scope, "summary", "FileAgent/SummaryAgent");
            printScopeValue(scope, "analysis", "AnalysisAgent");
        }
    }

    private static void validateEnvironment() {
        if (GITHUB_TOKEN == null || GITHUB_TOKEN.isBlank()) {
            System.err.println("Error: GITHUB_TOKEN environment variable not set");
            System.exit(1);
        }
    }

    private static McpClient createMcpClient() {
        String npmCmd = System.getProperty("os.name").toLowerCase().contains("win") ? "npm.cmd" : "npm";
        McpTransport transport = new StdioMcpTransport.Builder()
                .command(List.of(npmCmd, "exec", "@modelcontextprotocol/server-filesystem@2025.12.18", ALLOWED_DIRECTORY))
                .logEvents(false)
                .build();

        return new DefaultMcpClient.Builder()
                .transport(transport)
                .clientName("supervisor-agent-demo")
                .clientVersion("1.0.0")
                .toolExecutionTimeout(Duration.ofSeconds(60))
                .build();
    }

    private static ChatModel createChatModel() {
        return OpenAiOfficialChatModel.builder()
                .baseUrl("https://models.inference.ai.azure.com")
                .modelName("gpt-4.1")
                .apiKey(GITHUB_TOKEN)
                .build();
    }

    private static void printScopeValue(AgenticScope scope, String key, String source) {
        Object value = scope.readState(key);
        if (value != null) {
            String strValue = value.toString();
            String truncated = strValue.length() > 200 ? strValue.substring(0, 200) + "..." : strValue;
            System.out.println(key + " (" + source + "): " + truncated);
        }
    }

    private static AgentListener createAgentListener() {
        return new AgentListener() {
            @Override
            public void beforeAgentInvocation(AgentRequest request) {
                System.out.println("\n🚀 [EVENT] Starting agent: " + request.agentName());
                var inputs = request.inputs();
                if (inputs != null && !inputs.isEmpty()) {
                    inputs.forEach((k, v) -> {
                        String val = v != null ? v.toString() : "null";
                        String truncated = val.length() > 80 ? val.substring(0, 80) + "..." : val;
                        System.out.println("   📥 " + k + ": " + truncated);
                    });
                }
            }

            @Override
            public void afterAgentInvocation(AgentResponse response) {
                String res = response.output() != null ? response.output().toString() : "null";
                String truncated = res.length() > 100 ? res.substring(0, 100) + "..." : res;
                System.out.println("✅ [EVENT] Completed agent: " + response.agentName() + " -> " + truncated);
            }

            @Override
            public void onAgentInvocationError(AgentInvocationError error) {
                System.out.println("❌ [EVENT] Error in agent: " + error.agent().name() + " - " + error.error().getMessage());
            }

            @Override
            public boolean inheritedBySubagents() {
                return true;
            }
        };
    }
}
