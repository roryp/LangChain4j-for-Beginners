package com.example.langchain4j.mcp;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * AnalysisAgent - An agent that analyzes text content and provides insights.
 * Demonstrates how multiple agents can work together with different responsibilities.
 */
public interface AnalysisAgent {
    
    @SystemMessage("You are an expert text analyst. Analyze the given content and provide insights about its structure, tone, and key themes.")
    @UserMessage("Analyze the following content and provide insights:\n\n{{content}}")
    @Agent(description = "Analyzes text content and provides insights", outputKey = "analysis")
    String analyzeContent(@V("content") String content);
}
