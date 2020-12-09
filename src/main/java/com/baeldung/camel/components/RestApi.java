package com.baeldung.camel.components;

import com.baeldung.camel.pojo.OrderUserResponse;
import com.baeldung.camel.process.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class RestApi extends RouteBuilder {

    private static final String MICROSERVICE_ORDER_URL = "http://10.39.1.67:3000/orders";
    private static final String MICROSERVICE_USER_URL = "http://10.39.1.49:8088/api/v1/User";
    private static final String MICROSERVICE_PRODUCT_URL = "http://10.39.1.67:8082/products";


    @Value("${server.port}")
    String serverPort;

    @Value("${baeldung.api.path}")
    String contextPath;


    private final ObjectMapper objectMapper;

    public RestApi(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void configure() {
        CamelContext context = new DefaultCamelContext();
        restConfiguration().contextPath(contextPath) //
                .port(serverPort)
                .enableCORS(true)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Test REST API")
                .apiProperty("api.version", "v1")
                .apiProperty("cors", "true") // cross-site
                .apiContextRouteId("doc-api")
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true");

        rest("toures/bus/v1").description("Teste REST Service").id("api-route")
                .get("/orders?userId={userId}")
                    .bindingMode(RestBindingMode.json)
                    .outType(OrderUserResponse.class)
                    .to("direct:getOrdersInfo")
                .get("/orders/detail?orderId={orderId}")
                    .bindingMode(RestBindingMode.json)
                    .outType(OrderUserResponse.class)
                    .to("direct:getDetailOrderInfo");


        from("direct:getOrdersInfo")
                .routeId("direct-routeGet")
                .tracing()
                .to("direct:getOrdersByClient")
                .to("direct:getUserInfo")
                .to("direct:processOrderUserInfo");


        from("direct:getDetailOrderInfo")
                .routeId("direct-routeGet2")
                .tracing()
                .to("direct:getOrder")
                .to("direct:getUserInfo")
                .to("direct:getOrderProducts")
                .to("direct:getProductsInfo")
                .to("direct:processDetailOrderInfo");


        from("direct:getOrdersByClient")
                .setHeader(Exchange.HTTP_QUERY, simple("find_by_client_id=${header.userId}"))
                .setHeader(Exchange.HTTP_URI, constant(MICROSERVICE_ORDER_URL))
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .toD(MICROSERVICE_ORDER_URL)
                .process(new ProcessOrders(objectMapper))
                .end();

        from("direct:getUserInfo")
                .setHeader(Exchange.HTTP_PATH, simple("${header.userId}"))
                .setHeader(Exchange.HTTP_URI, constant(MICROSERVICE_USER_URL))
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .toD(MICROSERVICE_USER_URL)
                .process(new ProcessUserInfo(objectMapper))
                .end();

        from("direct:processOrderUserInfo")
                .process(new ProcessOrdersUsers())
                .end();

        from("direct:getOrder")
                .setHeader(Exchange.HTTP_PATH, simple("${header.orderId}"))
                .setHeader(Exchange.HTTP_URI, constant(MICROSERVICE_ORDER_URL))
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .toD(MICROSERVICE_ORDER_URL)
                .process(new ProcessOrder(objectMapper))
                .end();

        from("direct:getOrderProducts")
                .setHeader(Exchange.HTTP_PATH, simple("${header.orderId}/order_products"))
                .setHeader(Exchange.HTTP_URI, constant(MICROSERVICE_ORDER_URL))
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .toD(MICROSERVICE_ORDER_URL)
                .process(new ProcessOrderProducts(objectMapper))
                .end();

        from("direct:getProductsInfo")
                .removeHeaders("CamelHttp*")
                .setHeader(Exchange.HTTP_PATH, simple("findAllByIds/${exchangeProperty.productIds}"))
                .setHeader(Exchange.HTTP_URI, constant(MICROSERVICE_PRODUCT_URL))
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .toD(MICROSERVICE_PRODUCT_URL)
                .process(new ProcessProductsInfo(objectMapper))
                .end();

        from("direct:processDetailOrderInfo")
                .removeHeaders("CamelHttp*")
                .process(new ProcessDetailOrderInfo())
                .end();

    }
}
