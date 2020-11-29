package com.baeldung.camel.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;

public class ProcessSetUserRequest implements Processor {

    private final String microserviceUserUrl;

    public ProcessSetUserRequest(String microserviceUserUrl) {
        this.microserviceUserUrl = microserviceUserUrl;
    }

    public void process(Exchange exchange) throws Exception {
        List<Integer> userIds = (List<Integer>) exchange.getProperty("userIds");
        Integer index = (Integer) exchange.getProperty(Exchange.LOOP_INDEX);
        exchange.getIn().setHeader(Exchange.HTTP_URI, microserviceUserUrl);
        exchange.getIn().setHeader(Exchange.HTTP_PATH, userIds.get(index));
        exchange.getIn().setHeader(Exchange.HTTP_METHOD, "GET");

        exchange.setProperty("userId", userIds.get(index));
    }
}
