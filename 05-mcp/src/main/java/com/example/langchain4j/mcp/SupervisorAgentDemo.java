package com.example.langchain4j.mcp;

import com.example.langchain4j.mcp.agents.AnalysisAgent;
import com.example.langchain4j.mcp.agents.FileAgent;
import com.example.langchain4j.mcp.agents.SummaryAgent;
import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.agentic.supervisor.SupervisorAgent;
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
 * Supervisor Agent Demo - Demonstrates the Pure Agentic AI pattern using a Supervisor.
 * 
 * The Supervisor Agent pattern allows an LLM to autonomously decide which agents to invoke
 * based on the user's request. Unlike deterministic workflows (sequential, loop, parallel),
 * the supervisor dynamically generates an execution plan.
 * 
 * This demo showcases:
 * 1. FileAgent - Reads files using MCP filesystem tools
 * 2. AnalysisAgent - Analyzes content structure and themes
 * 3. SummaryAgent - Creates concise summaries
 * 
 * The Supervisor decides which agents to invoke based on the user request.
 * 
 * @see <a href="https://docs.langchain4j.dev/tutorials/agents/">LangChain4j Agents Documentation</a>
 */
public class SupervisorAgentDemo {

    private static final String GITHUB_TOKEN = System.getenv("GITHUB_TOKEN");
    private static final String ALLOWED_DIRECTORY = System.getProperty("user.dir");

    public static void main(String[] args) {
        System.out.println("Supervisor Agent Demo (Pure Agentic AI Pattern)");
        System.out.println("=".repeat(50));

        if (GITHUB_TOKEN == null || GITHUB_TOKEN.isBlank()) {
            System.err.println("Error: GITHUB_TOKEN environment variable not set");
            System.exit(1);
        }

        McpTransport transport = buildStdioTransport();
        McpClient mcpClient = buildMcpClient(transport);

        try {
            ToolProvider mcpToolProvider = McpToolProvider.builder()
                    .mcpClients(List.of(mcpClient))
                    .build();

            ChatModel model = buildChatModel();

            // Build individual agents with specific capabilities
            // Each agent has a description that helps the Supervisor decide when to use it
            
            // FileAgent: Has MCP tools to read files
            FileAgent fileAgent = AgenticServices.agentBuilder(FileAgent.class)
                    .chatModel(model)
                    .toolProvider(mcpToolProvider)
                    .build();

            // AnalysisAgent: Analyzes content for structure and themes
            AnalysisAgent analysisAgent = AgenticServices.agentBuilder(AnalysisAgent.class)
                    .chatModel(model)
                    .build();

            // SummaryAgent: Creates concise summaries
            SummaryAgent summaryAgent = AgenticServices.agentBuilder(SummaryAgent.class)
                    .chatModel(model)
                    .build();

            // Build the Supervisor Agent
            // The Supervisor uses an LLM to plan which agents to invoke
            SupervisorAgent supervisor = AgenticServices.supervisorBuilder()
                    .chatModel(model)  // The "planner" model that decides what to do
                    .subAgents(fileAgent, analysisAgent, summaryAgent)
                    .responseStrategy(SupervisorResponseStrategy.SUMMARY)  // Return a summary of all operations
                    .build();

            // Request 1: Ask for a file to be read and analyzed
            String filePath = ALLOWED_DIRECTORY + "/src/main/resources/file.txt";
            String request1 = "Read the file at " + filePath + " and analyze what it's about";
            
            System.out.println("\n📋 Request 1: Read and Analyze");
            System.out.println("User request: " + request1);
            System.out.println("-".repeat(50));
            
            // The Supervisor will autonomously:
            // 1. Recognize it needs to read a file → invoke FileAgent
            // 2. Recognize it needs to analyze → invoke AnalysisAgent
            String response1 = supervisor.invoke(request1);
            System.out.println("\n🤖 Supervisor Response:");
            System.out.println(response1);

            // Request 2: Ask for just a summary (Supervisor should choose SummaryAgent)
            System.out.println("\n" + "=".repeat(50));
            String content = "LangChain4j is a Java library that simplifies integrating AI/LLM capabilities. " +
                           "It provides unified APIs for various AI providers, supports RAG patterns, " +
                           "and enables building AI agents with tools and MCP integration.";
            String request2 = "Give me a brief summary of this: " + content;
            
            System.out.println("\n📋 Request 2: Summarize Content");
            System.out.println("User request: [asking for summary of LangChain4j description]");
            System.out.println("-".repeat(50));
            
            String response2 = supervisor.invoke(request2);
            System.out.println("\n🤖 Supervisor Response:");
            System.out.println(response2);

        } finally {
            try { mcpClient.close(); } catch (Exception ignored) {}
            System.exit(0);
        }
    }

    private static McpTransport buildStdioTransport() {
        String npmCommand = System.getProperty("os.name").toLowerCase().contains("win") ? "npm.cmd" : "npm";
        return new StdioMcpTransport.Builder()
                .command(List.of(npmCommand, "exec", "@modelcontextprotocol/server-filesystem@2025.12.18", ALLOWED_DIRECTORY))
                .logEvents(false)
                .build();
    }

    private static McpClient buildMcpClient(McpTransport transport) {
        return new DefaultMcpClient.Builder()
                .transport(transport)
                .clientName("supervisor-agent-demo")
                .clientVersion("1.0.0")
                .toolExecutionTimeout(Duration.ofSeconds(60))
                .build();
    }

    private static ChatModel buildChatModel() {
        return OpenAiOfficialChatModel.builder()
                .baseUrl("https://models.inference.ai.azure.com")
                .modelName("gpt-4.1")
                .apiKey(GITHUB_TOKEN)
                .build();
    }
}
