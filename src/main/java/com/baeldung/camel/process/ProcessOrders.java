package com.baeldung.camel.process;

import com.baeldung.camel.pojo.OrderResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;


public class ProcessOrders implements Processor {

    private final ObjectMapper objectMapper;

    public ProcessOrders(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void process(Exchange exchange) throws Exception {
        String orderResponse = exchange.getIn().getBody(String.class);
        List<OrderResponse> orderResponses = objectMapper.readValue(orderResponse, new TypeReference<List<OrderResponse>>(){});

        exchange.setProperty("orders", orderResponses);
    }
}
