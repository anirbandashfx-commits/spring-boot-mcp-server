package com.mcp.service.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class FileSystemTools {

    private static final Path BASE_DIR = Path.of(System.getenv()
        .getOrDefault("MCP_BASE_DIR", "/tmp/mcp-workspace"));

    @Tool(description = "List files in the workspace directory")
    public String listFiles(
        @ToolParam(description = "Subdirectory path relative to workspace, or empty for root")
        String subdir
    ) throws IOException {
        Path target = BASE_DIR.resolve(subdir).normalize();
        // prevent path traversal
        if (!target.startsWith(BASE_DIR)) return "Error: access denied";
        return Files.list(target)
            .map(p -> p.getFileName().toString())
            .collect(Collectors.joining("\n"));
    }

    @Tool(description = "Read the contents of a file in the workspace")
    public String readFile(
        @ToolParam(description = "File path relative to workspace") String path
    ) throws IOException {
        Path target = BASE_DIR.resolve(path).normalize();
        if (!target.startsWith(BASE_DIR)) return "Error: access denied";
        return Files.readString(target);
    }
}