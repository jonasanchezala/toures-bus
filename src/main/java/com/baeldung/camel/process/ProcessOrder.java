package com.baeldung.camel.process;

import com.baeldung.camel.pojo.OrderResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;


public class ProcessOrder implements Processor {

    private final ObjectMapper objectMapper;

    public ProcessOrder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void process(Exchange exchange) throws Exception {
        String orderResponse = exchange.getIn().getBody(String.class);
        OrderResponse orderResponses = objectMapper.readValue(orderResponse, OrderResponse.class);
        exchange.setProperty("order", orderResponses);
        exchange.getIn().setHeader("userId", orderResponses.getClient_id());
    }
}
