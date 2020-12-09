package com.baeldung.camel.process;

import com.baeldung.camel.pojo.CampaignResponse;
import com.baeldung.camel.pojo.OrderResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class ProcessCampaign implements Processor {

    private final ObjectMapper objectMapper;

    public ProcessCampaign(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void process(Exchange exchange) throws Exception {
        String campaignsResponse = exchange.getIn().getBody(String.class);
        CampaignResponse campaignResponse = objectMapper.readValue(campaignsResponse, CampaignResponse.class);
        exchange.setProperty("campaign", campaignResponse);
    }
}
