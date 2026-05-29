package com.frodo.ollamai.services;

import com.frodo.ollamai.domain.EmbeddingEntity;
import com.frodo.ollamai.repository.EmbeddingRepository;
import org.springframework.ai.document.Document;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OllamaService {

    private final EmbeddingRepository embeddingRepository;
    private final OllamaEmbeddingModel embeddingModel;

    public OllamaService(EmbeddingRepository embeddingRepository, OllamaEmbeddingModel embeddingModel) {
        this.embeddingRepository = embeddingRepository;
        this.embeddingModel = embeddingModel;
    }

    /**
     * 🧠 Accepts raw text, creates the vector embedding via Ollama,
     * and saves it to your custom EMBEDDINGS table.
     */
    public void embedAndStore(String rawText, String sourceName) {
        System.out.println("📄 Passing text payload to local Ollama embedding engine...");

        // ============================================
        // 🎯 FIX: THIS WAS THE MISSING STEP!
        // ============================================
        // This converts your plain text into a 768-dimension mathematical coordinate array
        float[] vectorData = embeddingModel.embed(rawText);

        System.out.println("🧬 Vector successfully created with dimensions: " + vectorData.length);

        // 2. Create your entity instance
        EmbeddingEntity entity = new EmbeddingEntity();
        entity.setContent(rawText);
        entity.setEmbedding(vectorData); // ◄─ Set the vector data we just generated!
        entity.setMetadata("{\"source\": \"" + sourceName + "\"}");

        // 3. Call the inherited save method
        String metadataJson = "{\"source\": \"" + sourceName + "\"}";
        embeddingRepository.insertCustom(null, rawText, metadataJson, vectorData);
        System.out.println("✅ Custom repository successfully saved entity to EMBEDDINGS table.");
    }
}
