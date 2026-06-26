# Spring Boot MCP Server

> Connects your existing Java services to AI agents — Claude, GitHub Copilot,
> Cursor — via SSE (IDE clients) and stdio (CLI clients).
> Drop in your own `@Tool` methods and your Java backend becomes AI-callable.

---

## What it does

Exposes ready-to-use MCP tools that any MCP-compatible AI agent can call:

| Tool | What it does | Try asking Claude |
|------|-------------|-------------------|
| `queryDatabase` | Read-only SQL with SELECT guard | "Show me the 5 most recent orders" |
| `listFiles` | Browse sandboxed workspace files | "What files are in the reports folder?" |
| `readFile` | Read file contents from workspace | "Read the contents of config.json" |
| `getCustomer` | Fetch customer from CRM REST API | "Get details for customer ID abc-123" |
| `searchOrders` | Filter orders by status | "Show me all PENDING orders" |

---

## Quick start

```bash
git clone https://github.com/anirbandashfx-commits/spring-boot-mcp-server.git
cd spring-boot-mcp-server
cp application.yml.example src/main/resources/application.yml
# Edit application.yml with your DB credentials
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

Restart Claude Desktop. Look for the 🔨 hammer icon in the chat input — your tools will be listed there.

---

## Connect to VS Code / Cursor (SSE)

Start the app first, then add to `.vscode/mcp.json`:

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

## The transport rule (saves you hours of debugging)

| Client | Transport | Use this |
|--------|-----------|----------|
| Claude Desktop, Claude Code CLI | stdio | `command: java -jar ...` |
| VS Code, Cursor, Windsurf | SSE | `url: http://localhost:8080/sse` |

Using the wrong transport causes **silent** connection failures with no useful error message.

---

## Add your own tools

```java
@Service
public class MyCustomTools {

    @Tool(description = "Describe what this tool does in plain English")
    public String myTool(
        @ToolParam(description = "What this parameter expects") String input
    ) {
        return result;
    }
}
```

Spring AI auto-registers any `@Service` with `@Tool` methods. No extra config needed.

---

## Built with

- Java 21 + Spring Boot 3.4
- Spring AI 1.0 MCP Server
- Tested: Claude Desktop (stdio), VS Code Agent mode (SSE)

---

## Need a custom MCP server for your Java team?

I build these for teams integrating AI agents into existing Spring Boot applications.
→ [Connect on LinkedIn](https://www.linkedin.com/in/anirbandas1986/)