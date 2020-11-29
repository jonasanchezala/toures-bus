package com.baeldung.camel.process;

import com.baeldung.camel.pojo.*;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ProcessDetailOrderInfo implements Processor {

    private static final String DATE_FORMAT = "yyyy-mm-dd hh:mm:ss";

    public void process(Exchange exchange) throws Exception {

        OrderResponse order  = (OrderResponse) exchange.getProperty("order");
        String userName = (String) exchange.getProperty("userName");
        List<ProductResponse> products = (List<ProductResponse>) exchange.getProperty("products");


        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();

        orderDetailResponse.setId(order.getId());
        orderDetailResponse.setName_client(userName);
        orderDetailResponse.setComments(order.getComments());
        orderDetailResponse.setTotal(order.getTotal());
        orderDetailResponse.setStatus(order.getStatus());
        orderDetailResponse.setCreated_at(dateFormat.format(order.getCreated_at()));
        orderDetailResponse.setUpdated_at(dateFormat.format(order.getUpdated_at()));
        orderDetailResponse.setPayment_method(order.getPayment_method());
        orderDetailResponse.setCredit_number_card(order.getCredit_number_card());
        orderDetailResponse.setProductResponses(products);

        exchange.getOut().setBody(orderDetailResponse);
    }
}
