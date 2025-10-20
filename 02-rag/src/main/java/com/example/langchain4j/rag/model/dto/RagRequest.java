package com.example.langchain4j.rag.model.dto;

/**
 * Request DTO for RAG questions.
 */
public record RagRequest(
    String question,
    String conversationId,
    Integer maxResults
) {
    public RagRequest {
        if (question == null || question.isBlank()) {
            throw new IllegalArgumentException("Question cannot be null or blank");
        }
    }
    
    public static RagRequest of(String question) {
        return new RagRequest(question, null, 5);
    }
}
