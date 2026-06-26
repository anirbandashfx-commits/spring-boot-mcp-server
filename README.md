# Spring Boot MCP Server

> Connects your existing Java services to AI agents — Claude, GitHub Copilot, 
> Cursor — via SSE (IDE clients) and stdio (CLI clients). Drop in your own 
> `@Tool` methods and you're done.

<!-- Replace with your actual GIF once recorded -->
![Demo: Claude calling database and file system tools via MCP](docs/demo.gif)

---

## What it does

Exposes 3 ready-to-use MCP tools that any MCP-compatible AI agent can call:

| Tool | What it does | Try asking Claude |
|------|-------------|-------------------|
| `queryDatabase` | Read-only SQL queries with SELECT guard | "Show me the 5 most recent orders" |
| `listFiles` | Browse sandboxed workspace files | "What files are in the reports folder?" |
| `readFile` | Read file contents from workspace | "Read the contents of config.json" |
| `getCustomer` | Fetch customer from REST API | "Get details for customer ID abc-123" |
| `searchOrders` | Filter orders by status | "Show me all PENDING orders" |

---

## Quick start

**1. Clone and configure**
```bash
git clone https://github.com/anirbandashfx-commits/spring-boot-mcp-server.git
cd spring-boot-mcp-server
cp application.yml.example src/main/resources/application.yml
# Edit application.yml with your DB credentials
```

**2. Run**
```bash
mvn spring-boot:run
```

Or with Docker:
```bash
docker compose up
```

---

## Connect to Claude Desktop (stdio)

Add to `~/Library/Application Support/Claude/claude_desktop_config.json`:

```json
{
  "mcpServers": {
    "spring-boot-mcp": {
      "command": "java",
      "args": ["-jar", "/absolute/path/to/target/spring-boot-mcp-server.jar"]
    }
  }
}
```

Restart Claude Desktop. Look for the 🔨 hammer icon in the chat input.

---

## Connect to VS Code / Cursor (SSE)

Start the app first (`mvn spring-boot:run`), then add to `.vscode/mcp.json`:

```json
{
  "servers": {
    "spring-boot-mcp": {
      "type": "sse",
      "url": "http://localhost:8080/sse"
    }
  }
}
```

---

## The transport rule (saves you hours)

| Client | Transport | Config needed |
|--------|-----------|---------------|
| Claude Desktop, Claude Code CLI | stdio | `command: java -jar ...` |
| VS Code, Cursor, Windsurf | SSE | `url: http://localhost:8080/sse` |

Using the wrong transport causes silent connection failures. 
See [docs/transport-guide.md](docs/transport-guide.md) for details.

---

## Add your own tools

```java
@Service
public class MyCustomTools {

    @Tool(description = "Describe what this tool does in plain English")
    public String myTool(
        @ToolParam(description = "What this parameter is") String input
    ) {
        // your logic here
        return result;
    }
}
```

That's it. Spring AI auto-registers any `@Service` with `@Tool` methods.

---

## Project structure
