package com.mcp.service.config;

import com.mcp.service.tools.ApiWrapperTools;
import com.mcp.service.tools.DatabaseMcpTools;
import com.mcp.service.tools.FileSystemTools;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpToolsConfig {

    @Bean
    public ToolCallbackProvider mcpTools(
            ApiWrapperTools apiWrapperTools,
            DatabaseMcpTools databaseMcpTools,
            FileSystemTools fileSystemTools) {

        return MethodToolCallbackProvider.builder()
                .toolObjects(apiWrapperTools, databaseMcpTools, fileSystemTools)
                .build();
    }
}