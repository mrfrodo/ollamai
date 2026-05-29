package com.frodo.ollamai.repository;

import com.frodo.ollamai.domain.EmbeddingEntity;
import org.springframework.data.jdbc.repository.query.Modifying; // ◄── Don't forget this import!
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmbeddingRepository extends ListCrudRepository<EmbeddingEntity, UUID> {

    /**
     * 💾 Custom Explicit Vector Insert
     * This is the missing piece! It forces PostgreSQL to convert your Java float array
     * into a native pgvector using the '::vector' typecast.
     */
    @Modifying
    @Query("""
        INSERT INTO embeddings (id, content, metadata, embedding) 
        VALUES (COALESCE(:id, gen_random_uuid()), :content, :metadata::jsonb, :embedding::vector)
    """)
    void insertCustom(@Param("id") UUID id,
                      @Param("content") String content,
                      @Param("metadata") String metadata,
                      @Param("embedding") float[] embedding);

    /**
     * 🔍 Custom Semantic Vector Search
     */
    @Query("""
        SELECT id, content, metadata, embedding 
        FROM embeddings 
        ORDER BY embedding <=> :queryVector::vector 
        LIMIT :topK
    """)
    List<EmbeddingEntity> findNearestNeighbors(@Param("queryVector") float[] queryVector, @Param("topK") int topK);
}