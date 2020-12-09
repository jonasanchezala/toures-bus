package com.baeldung.camel.process;

import com.baeldung.camel.pojo.OrderProductsResponse;
import com.baeldung.camel.pojo.OrderResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;
import java.util.stream.Collectors;


public class ProcessOrderProducts implements Processor {

    private final ObjectMapper objectMapper;

    public ProcessOrderProducts(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void process(Exchange exchange) throws Exception {
        String orderResponse = exchange.getIn().getBody(String.class);
        List<OrderProductsResponse> orderProducts = objectMapper.readValue(orderResponse, new TypeReference<List<OrderProductsResponse>>(){});
        List<String> productIds = orderProducts.stream().map(orderProduct -> Integer.toString(orderProduct.getProduct_id())).distinct().collect(Collectors.toList());
        exchange.setProperty("productIds", String.join(",", productIds));
        exchange.setProperty("orderProducts", orderProducts);
    }
}
