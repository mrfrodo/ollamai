# Ollama ai  

### Hexagonal architecture

### Domain driven design

### LangChain4J

### PGVector for embeddings

### Sring boot data jdbc

---

$ docker run --name pgvector \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
    -e POSTGRES_DB=postgres \
    -p 5432:5432 \
    -d pgvector/pgvector:pg17

$ docker exec -it pgvector psql -U postgres

postgres=# CREATE EXTENSION IF NOT EXISTS vector;

$ ollama serve > /dev/null 2>&1 &

$ curl http://localhost:11434
Ollama is running  

#### Download your Agent's Brain (Takes text/images, thinks, executes tools)
$ ollama pull gemma4:e2b

#### Download your Agent's Librarian (Converts data into vector numbers for PostgreSQL)
ollama pull embeddinggemma


---
### My AI agent
An AI Agent is an autonomous system driven by an LLM that doesn't just chat,   
but perceives an environment, makes decisions, and takes actions using tools.