package com.example.langchain4j.mcp.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * FileAgent - An agent that reads files using MCP filesystem tools.
 * 
 * This agent is equipped with MCP filesystem tools and can read files from the
 * allowed directory. It's the entry point in the file → report workflow:
 * 
 *   FileAgent (reads file) → ReportAgent (generates report)
 * 
 * The @Agent annotation marks this as an agentic AI service with:
 * - description: Helps the Supervisor understand when to invoke this agent
 * - outputKey: Stores the raw file content in 'fileContent' for downstream agents
 */
public interface FileAgent {
    
    @SystemMessage("You are a file reader. Use the available tools to read files. " +
                   "Return the file contents exactly as read, without modification or summarization.")
    @UserMessage("Read and return the complete contents of the file at {{path}}")
    @Agent(description = "Reads file contents from the filesystem", outputKey = "fileContent")
    String readFile(@V("path") String path);
}
