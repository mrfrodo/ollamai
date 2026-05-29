package com.frodo.ollamai.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

@Table("EMBEDDINGS") // 🎯 Binds this Java class directly to your custom table name
public class EmbeddingEntity {

    @Id
    private UUID id;

    private String content;

    // Stored as a raw string payload matching the JSONB column in PostgreSQL
    private String metadata;

    // pgvector 768-dimension arrays map natively to primitive float arrays in Spring Data JDBC
    private float[] embedding;

    // Default No-Args Constructor (Required by Spring Data Framework)
    public EmbeddingEntity() {
    }

    // Convenience Constructor for creation inside your Service Layer
    public EmbeddingEntity(String content, String metadata, float[] embedding) {
        this.content = content;
        this.metadata = metadata;
        this.embedding = embedding;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public float[] getEmbedding() {
        return embedding;
    }

    public void setEmbedding(float[] embedding) {
        this.embedding = embedding;
    }
}