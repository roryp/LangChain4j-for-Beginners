package com.example.langchain4j.mcp.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * FileAgent - An agent that reads and analyzes files using MCP tools.
 * 
 * This agent is equipped with MCP filesystem tools and can read files from the
 * allowed directory. It's typically the entry point in a multi-agent workflow
 * when file content needs to be processed.
 * 
 * The @Agent annotation marks this as an agentic AI service with:
 * - description: Helps the Supervisor understand when to invoke this agent
 * - outputKey: Stores the result in the agentic scope for other agents to access
 */
public interface FileAgent {
    
    @SystemMessage("You are a helpful file assistant. Use the available tools to read files.")
    @UserMessage("Read the file at {{path}} and summarize its contents.")
    @Agent(description = "Reads and summarizes file contents", outputKey = "summary")
    String readFile(@V("path") String path);
}
