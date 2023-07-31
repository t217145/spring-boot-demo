package com.cyrus822.camel.rest.route;

import java.util.List;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import com.cyrus822.camel.rest.domains.SellRequest;
import com.cyrus822.camel.rest.processors.RouteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

@Component
public class RestApiRouteBuilder extends RouteBuilder {

    @Autowired
    private RouteHandler handler;

    @Override
    public void configure() throws Exception {

        restConfiguration().component("jetty").contextPath("/api").port(8081).bindingMode(RestBindingMode.json);
        ;

        rest().get("/getSellRequest").produces("application/json").outType(List.class).to("direct:handleGet");
        // .to("bean:routeHandler?method=getAll()");

        rest().post("/addSellRequest").consumes(MediaType.APPLICATION_JSON_VALUE).type(SellRequest.class)
                .to("direct:handlePost");
        // .to("bean:routeHandler?method=addSellRequest");

        from("direct:handleGet")
                .log(LoggingLevel.INFO, "handling GET request")
                .bean(handler, "getAll")
                .log(LoggingLevel.INFO, "GET request handled");

        from("direct:handlePost")
                .log(LoggingLevel.INFO, "handling GET request")
                .bean(handler, "addSellRequest")
                .log(LoggingLevel.INFO, "GET request handled");
    }

}