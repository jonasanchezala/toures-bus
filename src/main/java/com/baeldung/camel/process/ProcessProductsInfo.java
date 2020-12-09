package com.baeldung.camel.process;

import com.baeldung.camel.pojo.OrderProductsResponse;
import com.baeldung.camel.pojo.ProductResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;
import java.util.stream.Collectors;


public class ProcessProductsInfo implements Processor {

    private final ObjectMapper objectMapper;

    public ProcessProductsInfo(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void process(Exchange exchange) throws Exception {
        String orderResponse = exchange.getIn().getBody(String.class);
        List<ProductResponse> products = objectMapper.readValue(orderResponse, new TypeReference<List<ProductResponse>>(){});

        List<OrderProductsResponse> orderProducts = (List<OrderProductsResponse>) exchange.getProperty("orderProducts");


        if(orderProducts !=null && !orderProducts.isEmpty()){
            products.stream().forEach(product ->
                    product.setQuantity(
                            orderProducts
                                    .stream()
                                    .filter(orderProductsResponse -> orderProductsResponse.getProduct_id() == product.getId())
                                    .findFirst()
                                    .get()
                                    .getCount())
            );
        }

        exchange.setProperty("products", products);
    }
}
