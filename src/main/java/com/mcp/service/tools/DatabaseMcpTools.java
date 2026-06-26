package com.mcp.service.tools;

import java.util.List;
import java.util.Map;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseMcpTools {

    @Autowired private JdbcTemplate jdbc;

    @Tool(description = "Run a read-only SQL query on the application database")
    public String queryDatabase(
        @ToolParam(description = "SQL SELECT query to execute") String sql
    ) {
        // guard against writes
        if (!sql.trim().toUpperCase().startsWith("SELECT")) {
            return "Error: only SELECT queries are permitted";
        }
        List<Map<String, Object>> rows = jdbc.queryForList(sql);
        return rows.toString();
    }

    @Tool(description = "List all tables in the database schema")
    public String listTables() {
        // H2 uses uppercase 'PUBLIC' schema by default
        return jdbc.queryForList(
            "SELECT table_name FROM information_schema.tables WHERE table_schema = 'PUBLIC'"
        ).toString();
    }
}