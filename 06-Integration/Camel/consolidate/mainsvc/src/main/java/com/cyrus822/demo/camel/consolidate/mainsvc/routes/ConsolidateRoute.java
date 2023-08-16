package com.cyrus822.demo.camel.consolidate.mainsvc.routes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import com.cyrus822.demo.camel.consolidate.mainsvc.domains.DemoResponse;
import com.cyrus822.demo.camel.consolidate.mainsvc.processors.DemoProcessor;

@Component
@Configuration
public class ConsolidateRoute extends RouteBuilder {

    private static final Map<Integer, String> ERROR_MESSAGES = new HashMap<>();

    private Logger logger = LoggerFactory.getLogger(ConsolidateRoute.class);

    static {
        ERROR_MESSAGES.put(400, "Client not found");
        ERROR_MESSAGES.put(402, "Policy outdated");
        ERROR_MESSAGES.put(500, "Server Error");
    }

    @Override
    @SuppressWarnings({ "unchecked", "deprecation" })
    public void configure() throws Exception {
        logger.info("start");

        from("direct:start")
                .multicast(new ConsolidateStrategy())
                .parallelProcessing()
                .to("bean:webAPIProcessor?method=callSvc01",
                        "bean:webAPIProcessor?method=callSvc02",
                        "bean:webAPIProcessor?method=callSvc03")
                .end()
                .process(exchange -> {
                    logger.info("01");
                    List<DemoResponse> responseCodes = exchange.getIn().getBody(List.class);
                    logger.info("02");
                    boolean hasNon200Response = responseCodes.stream().anyMatch(resp -> resp.getResponseId() != 200);
                    logger.info("03");
                    if (hasNon200Response) {
                        List<String> respData = responseCodes.stream().map(r -> Integer.toString(r.getResponseId()))
                                .toList();
                        logger.info("04: {}", String.join(",", respData));

                        List<String> errorMessages = responseCodes.stream().distinct()
                                .filter(resp -> resp.getResponseId() != 200
                                        && ERROR_MESSAGES.containsKey(resp.getResponseId()))
                                .map(resp -> ERROR_MESSAGES.get(resp.getResponseId()))
                                .collect(Collectors.toList());

                        logger.info("05 : {}", String.join(",", errorMessages));
                        errorMessages.addAll(
                                responseCodes.stream().distinct()
                                        .filter(resp -> resp.getResponseId() != 200
                                                && !ERROR_MESSAGES.containsKey(resp.getResponseId()))
                                        .map(DemoResponse::getResponseData)
                                        .collect(Collectors.toList()));
                        logger.info("06");
                        String rtn = String.join(",", errorMessages);
                        logger.info("07");
                        exchange.getOut().setBody(rtn);
                        logger.info("08");
                        logger.error(rtn);
                    } else {
                        logger.info("04a");
                        List<String> successMessage = responseCodes.stream().map(resp -> resp.getResponseData())
                                .toList();
                        logger.info("05a");
                        String rtn = String.join(",", successMessage);
                        logger.info("06a");
                        exchange.getOut().setBody(rtn);
                        logger.info("07a");
                        logger.info(rtn);
                    }
                    logger.info("end");
                });
    }// end of configure()

    @Bean
    public DemoProcessor webAPIProcessor() {
        return new DemoProcessor();
    }

    @SuppressWarnings("unchecked")
    public static class ConsolidateStrategy implements AggregationStrategy {
        @Override
        public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
            List<DemoResponse> allResponse;
            if (oldExchange == null) {
                allResponse = new ArrayList<>();
            } else {
                allResponse = oldExchange.getIn().getBody(List.class);
            }

            DemoResponse newResponse = newExchange.getIn().getBody(DemoResponse.class);

            allResponse.add(newResponse);
            newExchange.getIn().setBody(allResponse);
            return newExchange;
        }
    }
}