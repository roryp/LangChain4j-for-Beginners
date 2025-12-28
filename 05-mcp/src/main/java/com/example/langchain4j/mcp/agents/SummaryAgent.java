package com.example.langchain4j.mcp.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * SummaryAgent - An agent that creates concise summaries of text content.
 * 
 * This agent specializes in distilling content into brief, clear summaries.
 * It's useful when you need a quick overview without detailed analysis.
 * 
 * In the Supervisor pattern, this agent is chosen when the user explicitly
 * asks for summaries or brief overviews of content.
 * 
 * The @Agent annotation marks this as an agentic AI service with:
 * - description: Helps the Supervisor understand when to invoke this agent
 * - outputKey: Stores the summary result for other agents to access
 */
public interface SummaryAgent {
    
    @SystemMessage("You are an expert summarizer. Create clear, concise summaries that capture key points.")
    @UserMessage("Create a brief summary (2-3 sentences) of the following content:\n\n{{content}}")
    @Agent(description = "Creates concise summaries of text content", outputKey = "summary")
    String summarize(@V("content") String content);
}
