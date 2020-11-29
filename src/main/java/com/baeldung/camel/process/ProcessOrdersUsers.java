package com.baeldung.camel.process;

import com.baeldung.camel.pojo.OrderResponse;
import com.baeldung.camel.pojo.OrderUserResponse;
import com.baeldung.camel.pojo.UserResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ProcessOrdersUsers implements Processor {

    private static final String DATE_FORMAT = "yyyy-mm-dd hh:mm:ss";

    public void process(Exchange exchange) throws Exception {

        List<OrderResponse> orders  = (List<OrderResponse>) exchange.getProperty("orders");
        String userName = (String) exchange.getProperty("userName");
        List<OrderUserResponse> orderUserResponses = new ArrayList<>();
        
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        orders.stream().forEach(orderResponse -> {
            OrderUserResponse orderUserResponse = new OrderUserResponse();
            orderUserResponse.setId(orderResponse.getId());
            orderUserResponse.setClient_name(userName);
            orderUserResponse.setTotal(new Double(orderResponse.getTotal()));
            orderUserResponse.setStatus(orderResponse.getStatus());
            orderUserResponse.setCreated_at(dateFormat.format(orderResponse.getCreated_at()));
            orderUserResponse.setUpdated_at(dateFormat.format(orderResponse.getUpdated_at()));
            orderUserResponse.setPayment_method(orderResponse.getPayment_method());
            orderUserResponse.setCredit_number_card(orderResponse.getCredit_number_card());

            orderUserResponses.add(orderUserResponse);
        });

        exchange.getOut().setBody(orderUserResponses);
    }
}
