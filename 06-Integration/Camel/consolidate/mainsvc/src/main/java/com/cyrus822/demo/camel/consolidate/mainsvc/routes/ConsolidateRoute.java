package com.cyrus822.demo.camel.consolidate.mainsvc.routes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyrus822.demo.camel.consolidate.mainsvc.domains.DemoRequest;
import com.cyrus822.demo.camel.consolidate.mainsvc.domains.DemoResponse;
import com.cyrus822.demo.camel.consolidate.mainsvc.processors.DemoBean;
import com.cyrus822.demo.camel.consolidate.mainsvc.processors.DemoProcessor;

import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.support.SimpleRegistry;

@Component
public class ConsolidateRoute extends RouteBuilder {

    @Autowired
    private DemoBean dBean;

    @Autowired
    private DemoProcessor dPro;    

    private static final Map<Integer, String> ERROR_MESSAGES = new HashMap<>();

    private Logger logger = LoggerFactory.getLogger(ConsolidateRoute.class);

    static {
        ERROR_MESSAGES.put(400, "Client not found");
        ERROR_MESSAGES.put(402, "Policy outdated");
        ERROR_MESSAGES.put(500, "Server Error");
    }    

    @Override
    @SuppressWarnings({"unchecked", "deprecation", "unlikely-arg-type"})
    public void configure() throws Exception {
        SimpleRegistry simpleRegistry = new SimpleRegistry(); // this class is just a mapper
        simpleRegistry.bind("DemoBean", dBean);
        simpleRegistry.bind("DemoProcessor", dPro);
        CamelContext camelContext = new DefaultCamelContext(simpleRegistry);

        camelContext.start();

        logger.info("start");

        restConfiguration().component("jetty").contextPath("/api").port(8084).bindingMode(RestBindingMode.json);
        rest().get("/demo").produces("application/json").outType(String.class).to("direct:start");

        DemoRequest req01 = new DemoRequest("invalid c01", "Testing c01");
        DemoRequest req02 = new DemoRequest("c02", "Testing c02 s02");
        DemoRequest req03 = new DemoRequest("invalid c03", "Testing c03");

        from("direct:start")
            .autoStartup(true)
            .to("bean:DemoBean?method=demo()");

        from("direct:start11")
            .autoStartup(true)
            .multicast().parallelProcessing()
            .setHeader("svc01", constant(req01))
            .setHeader("svc02", constant(req02))
            .setHeader("svc03", constant(req03))
            .to("bean:DemoProcessor?method=callSvc01(${svc01})", "bean:DemoProcessor?method=callSvc02(${svc02})", "bean:DemoProcessor?method=callSvc03(${svc03})")
            .end()
            .process(exchange -> {
                List<DemoResponse> responseCodes = exchange.getIn().getBody(List.class);

                boolean hasNon200Response = responseCodes.stream().anyMatch(resp -> resp.getResponseId() != 200);

                if (hasNon200Response) {
                    List<String> errorMessages = responseCodes.stream()
                            .filter(resp -> resp.getResponseId() != 200 && ERROR_MESSAGES.containsKey(resp.getResponseId()))
                            .map(ERROR_MESSAGES::get)
                            .collect(Collectors.toList());
                    
                    errorMessages.addAll(
                        responseCodes.stream()
                            .filter(resp -> resp.getResponseId() != 200 && ERROR_MESSAGES.containsKey(resp.getResponseId()))
                            .map(resp -> resp.getResponseData())
                            .collect(Collectors.toList())
                    );

                    String rtn = String.join(",", errorMessages);
                    exchange.getOut().setBody(rtn);
                    logger.error(rtn);
                } else {
                    List<String> successMessage = responseCodes.stream().map(resp -> resp.getResponseData()).toList();
                    String rtn = String.join(",", successMessage);
                    exchange.getOut().setBody(rtn);
                    logger.info(rtn);
                }
            });

            
        camelContext.close();
    }
}
