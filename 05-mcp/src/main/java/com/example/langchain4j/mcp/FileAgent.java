package com.example.langchain4j.mcp;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * FileAgent - An agent that reads and analyzes files using MCP tools.
 * The @Agent annotation marks this as an agentic AI service.
 */
public interface FileAgent {
    
    @SystemMessage("You are a helpful file assistant. Use the available tools to read files.")
    @UserMessage("Read the file at {{path}} and summarize its contents.")
    @Agent(description = "Reads and summarizes file contents", outputKey = "summary")
    String readFile(@V("path") String path);
}
