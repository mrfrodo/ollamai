-- 🧹 Clean up old layouts
DROP TABLE IF EXISTS vector_store CASCADE;
DROP TABLE IF EXISTS embeddings CASCADE;

-- 🏗️ Build the new clean schema
CREATE TABLE IF NOT EXISTS embeddings (
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    content text,
    metadata jsonb,
    embedding vector(768),
    CONSTRAINT embeddings_pkey PRIMARY KEY (id)
);

-- 🌱 Insert your Telehouse Docklands seed line
INSERT INTO embeddings (content, metadata, embedding)
VALUES (
    'Node LND-CORE-SW1 is situated in Telehouse Docklands. It serves as the primary transmission switch connecting Greater London East backhaul loops.',
    '{"source_file": "manual_seed.sql", "network_layer": "core", "region": "Docklands"}',
    array_fill(0.1::float4, ARRAY[768])::vector
);