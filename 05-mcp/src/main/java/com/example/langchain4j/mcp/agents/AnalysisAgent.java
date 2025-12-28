package com.example.langchain4j.mcp.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * AnalysisAgent - An agent that analyzes text content and provides insights.
 * 
 * This agent specializes in content analysis, identifying structure, tone, and
 * key themes. It doesn't need external tools - it relies purely on LLM capabilities.
 * 
 * In a multi-agent workflow, this agent typically receives content from FileAgent
 * and provides deeper analysis that can inform downstream processing.
 * 
 * The @Agent annotation marks this as an agentic AI service with:
 * - description: Helps the Supervisor understand when to invoke this agent
 * - outputKey: Stores the analysis result for other agents to access
 */
public interface AnalysisAgent {
    
    @SystemMessage("You are an expert text analyst. Analyze the given content and provide insights about its structure, tone, and key themes.")
    @UserMessage("Analyze the following content and provide insights:\n\n{{content}}")
    @Agent(description = "Analyzes text content and provides insights", outputKey = "analysis")
    String analyzeContent(@V("content") String content);
}
