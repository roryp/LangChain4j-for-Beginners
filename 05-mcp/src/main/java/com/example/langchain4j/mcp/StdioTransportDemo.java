package com.example.langchain4j.mcp;

import java.io.File;
import java.time.Duration;
import java.util.List;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openaiofficial.OpenAiOfficialChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.ToolProvider;

/**
 * StdioTransportDemo - MCP Integration via Stdio Transport
 * Run: mvn exec:java -Dexec.mainClass="com.example.langchain4j.mcp.StdioTransportDemo"
 * 
 * Demonstrates MCP integration via stdio transport for filesystem operations.
 * 
 * This example shows how to spawn a local MCP server as a subprocess,
 * communicate through standard input/output, and use its tools for
 * file system operations.
 * 
 * Prerequisites:
 * - npm installed
 * - GITHUB_TOKEN environment variable set
 * 
 * Run this example:
 * 1. export GITHUB_TOKEN=your_token_here
 * 2. cd 05-mcp
 * 3. mvn compile exec:java -Dexec.mainClass=com.example.langchain4j.mcp.StdioTransportDemo
 * 
 * Key Concepts:
 * - StdioMcpTransport for local subprocess servers
 * - Process spawning and lifecycle management
 * - File system tool integration
 * - Cross-platform path handling
 * 
 * 💡 Ask GitHub Copilot:
 * - "How does Stdio transport work and when should I use it vs HTTP?"
 * - "How does LangChain4j manage the lifecycle of spawned MCP server processes?"
 * - "What are the security implications of giving AI access to the file system?"
 * - "How can I limit which directories or files the MCP filesystem server can access?"
 */
public class StdioTransportDemo {

    private static final String TARGET_FILE = "src/main/resources/file.txt";
    private static final String GITHUB_MODELS_URL = "https://models.github.ai/inference";
    private static final String MODEL_NAME = "gpt-4.1-nano";

    public static void main(String[] args) throws Exception {

        // Configure chat model
        ChatModel chatModel = buildChatModel();

        // Setup stdio transport with filesystem server
        McpTransport stdioTransport = buildStdioTransport();

        // Initialize MCP client with try-with-resources for automatic cleanup
        try (McpClient client = buildMcpClient(stdioTransport)) {
            
            // Create tool provider
            ToolProvider tools = McpToolProvider.builder()
                    .mcpClients(List.of(client))
                    .build();

            // Build AI service
            Bot assistant = AiServices.builder(Bot.class)
                    .chatModel(chatModel)
                    .toolProvider(tools)
                    .build();

            File targetFile = new File(TARGET_FILE);
            String query = String.format(
                "Read and summarize the content from: %s", 
                targetFile.getAbsolutePath()
            );
            String result = assistant.chat(query);
            System.out.println("Assistant response: " + result);
        }
        
        // Force exit to cleanup OkHttp connection pool threads
        System.exit(0);
    }

    private static ChatModel buildChatModel() {
        return OpenAiOfficialChatModel.builder()
                .baseUrl(GITHUB_MODELS_URL)
                .apiKey(System.getenv("GITHUB_TOKEN"))
                .modelName(MODEL_NAME)
                .timeout(Duration.ofSeconds(20))
                .strictTools(false)
                .build();
    }

    private static McpTransport buildStdioTransport() {
        // Determine npm command based on OS
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase()
                .contains("win");
        String npmCmd = isWindows ? "npm.cmd" : "npm";

        // Get absolute path to resources directory
        File resourcesFile = new File("src/main/resources");
        if (!resourcesFile.exists()) {
            throw new RuntimeException(
                "Resources directory not found. Current working directory: " + 
                new File(".").getAbsolutePath() + 
                ". Please run from the 05-mcp directory."
            );
        }
        
        // Normalize path for Node.js (use forward slashes even on Windows)
        String resourcesDir = resourcesFile.getAbsolutePath()
                .replace("\\", "/");

        return new StdioMcpTransport.Builder()
                .command(List.of(
                    npmCmd, "exec",
                    "@modelcontextprotocol/server-filesystem@0.6.2",
                    resourcesDir
                ))
                .logEvents(false)
                .build();
    }

    private static McpClient buildMcpClient(McpTransport transport) {
        return new DefaultMcpClient.Builder()
                .transport(transport)
                .autoHealthCheck(false)
                .initializationTimeout(Duration.ofSeconds(20))
                .toolExecutionTimeout(Duration.ofSeconds(20))
                .build();
    }
}
