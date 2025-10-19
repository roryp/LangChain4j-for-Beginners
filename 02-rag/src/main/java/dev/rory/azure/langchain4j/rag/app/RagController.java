package dev.rory.azure.langchain4j.rag.app;

import dev.rory.azure.langchain4j.rag.model.dto.RagRequest;
import dev.rory.azure.langchain4j.rag.model.dto.RagResponse;
import dev.rory.azure.langchain4j.rag.service.RagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for RAG (Retrieval-Augmented Generation) queries.
 */
@RestController
@RequestMapping("/api/rag")
public class RagController {

    private static final Logger log = LoggerFactory.getLogger(RagController.class);

    private final RagService ragService;

    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    /**
     * Ask a question using RAG.
     *
     * @param request RAG request with question
     * @return answer with sources
     */
    @PostMapping("/ask")
    public ResponseEntity<RagResponse> ask(@RequestBody RagRequest request) {
        log.info("Received RAG question: {}", request.question());

        try {
            // Validate request
            if (request.question() == null || request.question().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            // Process RAG request
            RagResponse response = ragService.ask(request);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("RAG request failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Health check endpoint.
     *
     * @return health status
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("RAG service is healthy");
    }
}
