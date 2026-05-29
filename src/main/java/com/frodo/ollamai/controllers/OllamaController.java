package com.frodo.ollamai.controllers;

import com.frodo.ollamai.services.OllamaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@RestController
public class OllamaController {

    private final OllamaService ollamaService;

    public OllamaController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadAndEmbedTopology(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ File is empty. Please upload a valid text file.");
        }

        try {
            // Read the uploaded file contents into a clean Java String
            String fileContent = new BufferedReader(
                    new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            // Pass the plain text to our service layer to process and store the vector embeddings
            ollamaService.embedAndStore(fileContent, file.getOriginalFilename());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("✅ File '" + file.getOriginalFilename() + "' successfully read, embedded, and stored in pgvector!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Failed to process and embed file: " + e.getMessage());
        }
    }

}
