# Claude Desktop Setup

Instructions for connecting Claude Desktop to the MCP Studio server.

1. Start the MCP Studio server:
   ```bash
   mvn spring-boot:run
   ```
2. Ensure the server is available at `http://localhost:8081/sse`.
3. Configure Claude Desktop to use SSE transport with the server URL above.
