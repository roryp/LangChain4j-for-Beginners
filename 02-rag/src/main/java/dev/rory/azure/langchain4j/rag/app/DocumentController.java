package dev.rory.azure.langchain4j.rag.app;

import dev.rory.azure.langchain4j.rag.model.dto.DocumentResponse;
import dev.rory.azure.langchain4j.rag.model.dto.ErrorResponse;
import dev.rory.azure.langchain4j.rag.service.DocumentService;
import dev.rory.azure.langchain4j.rag.service.EmbeddingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * REST controller for document upload and management.
 */
@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private static final Logger log = LoggerFactory.getLogger(DocumentController.class);

    private final DocumentService documentService;
    private final EmbeddingService embeddingService;

    public DocumentController(
            DocumentService documentService,
            EmbeddingService embeddingService) {
        this.documentService = documentService;
        this.embeddingService = embeddingService;
    }

    /**
     * Upload and process a document.
     *
     * @param file document file (PDF or TXT)
     * @return processing result
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadDocument(@RequestParam("file") MultipartFile file) {
        log.info("Received document upload: {}", file.getOriginalFilename());

        try {
            // Validate file
            if (file.isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Invalid file", "File is empty"));
            }

            String filename = file.getOriginalFilename();
            if (filename == null) {
                return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Invalid file", "Filename is missing"));
            }

            // Process document
            try (InputStream inputStream = file.getInputStream()) {
                DocumentService.ProcessedDocument processed = documentService.processDocument(
                    inputStream,
                    filename
                );

                // Store embeddings
                int embeddingCount = embeddingService.storeSegments(processed.segments());

                log.info("Successfully processed document: {} ({} segments)", 
                    filename, embeddingCount);

                return ResponseEntity.ok(new DocumentResponse(
                    processed.documentId(),
                    filename,
                    "Document processed successfully",
                    embeddingCount
                ));
            }

        } catch (Exception e) {
            log.error("Failed to process document", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Processing failed", e.getMessage()));
        }
    }

    /**
     * Health check endpoint.
     *
     * @return health status
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Document service is healthy");
    }
}
