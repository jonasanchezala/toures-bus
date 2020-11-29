package com.baeldung.camel.process;

import com.baeldung.camel.pojo.OrderResponse;
import com.baeldung.camel.pojo.UserResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProcessUserInfo implements Processor {

    private final ObjectMapper objectMapper;

    public ProcessUserInfo(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void process(Exchange exchange) throws Exception {
        String userResponse = exchange.getIn().getBody(String.class);
        UserResponse user = objectMapper.readValue(userResponse, UserResponse.class);
        exchange.setProperty("userName", user.getName());
    }
}
