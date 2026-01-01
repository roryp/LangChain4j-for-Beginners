package com.example.langchain4j.mcp.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * ReportAgent - An agent that generates structured reports from content.
 * 
 * This agent is the second step in the file → report workflow:
 * 
 *   FileAgent (reads file) → ReportAgent (generates report)
 * 
 * It receives the raw file content from FileAgent (via 'fileContent' in the
 * agentic scope) and produces a structured report with key findings.
 * 
 * The @Agent annotation marks this as an agentic AI service with:
 * - description: Helps the Supervisor understand when to invoke this agent
 * - outputKey: Stores the final report for the user
 */
public interface ReportAgent {
    
    @SystemMessage("You are a report writer. Generate clear, structured reports with: " +
                   "1) Executive Summary (2-3 sentences), " +
                   "2) Key Points (bullet list), " +
                   "3) Recommendations (if applicable). " +
                   "Keep it concise but informative.")
    @UserMessage("Generate a structured report based on the following content:\n\n{{content}}")
    @Agent(description = "Generates structured reports from content", outputKey = "report")
    String generateReport(@V("content") String content);
}
