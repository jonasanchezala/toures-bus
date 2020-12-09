package com.baeldung.camel.process;

import com.baeldung.camel.pojo.CampaignProductsResponse;
import com.baeldung.camel.pojo.OrderProductsResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;
import java.util.stream.Collectors;


public class ProcessCampaignProducts implements Processor {

    private final ObjectMapper objectMapper;

    public ProcessCampaignProducts(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void process(Exchange exchange) throws Exception {
        String campaignProductResponse = exchange.getIn().getBody(String.class);
        List<CampaignProductsResponse> campaignProductsResponse = objectMapper.readValue(campaignProductResponse, new TypeReference<List<CampaignProductsResponse>>(){});
        List<String> productIds = campaignProductsResponse.stream().map(orderProduct -> Integer.toString(orderProduct.getProduct_id())).distinct().collect(Collectors.toList());
        exchange.setProperty("productIds", String.join(",", productIds));
        exchange.setProperty("campaignProducts", campaignProductsResponse);
    }
}
