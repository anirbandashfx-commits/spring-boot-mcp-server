# MCP Studio

This repository contains the MCP server implementation and configuration for Spring AI.

## Project Structure

- `src/main/java/com/mcp/service/tools/` - MCP tool implementations
- `src/main/java/com/mcp/service/McpServerApplication.java` - application entry point
- `docs/` - setup guides for clients
- `docker-compose.yml` - local compose configuration
- `application.yml.example` - example Spring Boot configuration
- `mcp.json` - MCP server metadata and tool registration

## Usage

Run with Maven:

```bash
mvn spring-boot:run
```

Or build:

```bash
mvn package
```
