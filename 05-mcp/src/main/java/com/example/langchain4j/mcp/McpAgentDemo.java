package com.example.langchain4j.mcp;

import dev.langchain4j.agentic.AgenticServices;
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
 * MCP Agent Demo - Uses the LangChain4j Agentic module with MCP tools.
 * 
 * Run: mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.McpAgentDemo
 */
public class McpAgentDemo {

    private static final String GITHUB_TOKEN = System.getenv("GITHUB_TOKEN");
    private static final String ALLOWED_DIRECTORY = System.getProperty("user.dir");

    public static void main(String[] args) {
        System.out.println("MCP Agent Demo (using langchain4j-agentic)");
        System.out.println("==========================================");

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

            // Build agent using AgenticServices (from langchain4j-agentic module)
            FileAgent agent = AgenticServices.agentBuilder(FileAgent.class)
                    .chatModel(model)
                    .toolProvider(mcpToolProvider)
                    .build();

            String filePath = ALLOWED_DIRECTORY + "/src/main/resources/file.txt";
            System.out.println("\nReading: " + filePath);
            
            String response = agent.readFile(filePath);
            System.out.println("\nAgent Response:");
            System.out.println(response);

        } finally {
            try { mcpClient.close(); } catch (Exception ignored) {}
            // Force exit to clean up OkHttp threads
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
                .clientName("mcp-agent-demo")
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
