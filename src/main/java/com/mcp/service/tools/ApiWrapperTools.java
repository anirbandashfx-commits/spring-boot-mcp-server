package com.mcp.service.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiWrapperTools {

    private final RestTemplate restTemplate;

    @Autowired
    public ApiWrapperTools(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Tool(description = "Fetch customer data by ID from the internal CRM API")
    public String getCustomer(
        @ToolParam(description = "Customer UUID") String customerId
    ) {
        return restTemplate.getForObject(
            "/api/customers/{id}",
            String.class,
            customerId
        );
    }

    @Tool(description = "Search orders by status: PENDING, SHIPPED, DELIVERED, CANCELLED")
    public String searchOrders(
        @ToolParam(description = "Order status filter") String status,
        @ToolParam(description = "Maximum results to return, 1–50") int limit
    ) {
        return restTemplate.getForObject(
            "/api/orders?status={s}&limit={l}",
            String.class,
            status,
            Math.min(limit, 50)
        );
    }
}