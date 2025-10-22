package com.example.langchain4j.rag.service;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.azure.AzureOpenAiChatModel;
import dev.langchain4j.model.azure.AzureOpenAiEmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import com.example.langchain4j.rag.model.dto.RagRequest;
import com.example.langchain4j.rag.model.dto.RagResponse;
import com.example.langchain4j.rag.model.dto.SourceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for Retrieval-Augmented Generation (RAG).
 * Combines semantic search with LLM generation.
 */
@Service
public class RagService {

    private static final Logger log = LoggerFactory.getLogger(RagService.class);
    
    private static final int MAX_RESULTS = 5;
    private static final double MIN_SCORE = 0.7;

    private final AzureOpenAiChatModel chatModel;
    private final AzureOpenAiEmbeddingModel embeddingModel;
    private final EmbeddingStore<TextSegment> embeddingStore;

    public RagService(
            AzureOpenAiChatModel chatModel,
            AzureOpenAiEmbeddingModel embeddingModel,
            EmbeddingStore<TextSegment> embeddingStore) {
        this.chatModel = chatModel;
        this.embeddingModel = embeddingModel;
        this.embeddingStore = embeddingStore;
    }

    /**
     * Answer a question using retrieval-augmented generation.
     *
     * @param request RAG request with question
     * @return RAG response with answer and sources
     */
    public RagResponse ask(RagRequest request) {
        log.info("Processing RAG request: '{}'", request.question());

        try {
            // 1. Embed the question
            Embedding questionEmbedding = embeddingModel.embed(request.question()).content();
            
            // 2. Find relevant document segments using search
            EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(questionEmbedding)
                .maxResults(MAX_RESULTS)
                .minScore(MIN_SCORE)
                .build();
            
            EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);
            List<EmbeddingMatch<TextSegment>> matches = searchResult.matches();
            
            if (matches.isEmpty()) {
                log.warn("No relevant documents found for question: '{}'", request.question());
                return new RagResponse(
                    "I cannot answer this question based on the provided documents. " +
                    "Please try asking something related to the uploaded content.",
                    request.conversationId(),
                    new ArrayList<>()
                );
            }
            
            // 3. Build context from retrieved segments
            String context = matches.stream()
                .map(match -> match.embedded().text())
                .collect(Collectors.joining("\n\n"));
            
            // 4. Create prompt with context
            String prompt = String.format("""
                Answer the question based on the following context. 
                If the answer cannot be found in the context, say so.
                
                Context:
                %s
                
                Question: %s
                
                Answer:""", context, request.question());
            
            // 5. Generate answer
            String answer = chatModel.chat(prompt);
            
            // 6. Build source references
            List<SourceReference> sources = matches.stream()
                .map(match -> {
                    TextSegment segment = match.embedded();
                    String filename = segment.metadata().getString("filename");
                    return new SourceReference(
                        filename != null ? filename : "unknown",
                        segment.text(),
                        match.score()
                    );
                })
                .collect(Collectors.toList());

            log.info("RAG response generated with {} sources", sources.size());
            
            return new RagResponse(answer, request.conversationId(), sources);

        } catch (Exception e) {
            log.error("RAG processing failed", e);
            throw new RuntimeException("Failed to process question: " + e.getMessage(), e);
        }
    }
}
