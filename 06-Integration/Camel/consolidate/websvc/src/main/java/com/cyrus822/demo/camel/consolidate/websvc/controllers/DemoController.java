package com.cyrus822.demo.camel.consolidate.websvc.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.cyrus822.demo.camel.consolidate.websvc.domains.DemoRequest;
import com.cyrus822.demo.camel.consolidate.websvc.domains.DemoResponse;

@RestController
public class DemoController {

    @Value("${app.id}")
    private String appId;

    @Value("${server.port}")
    private int port;

    @PostMapping({ "", "/" })
    public @ResponseBody DemoResponse index(@RequestBody DemoRequest request) {
        DemoResponse response = new DemoResponse();
        if (request.getCusData().contains(appId)) {
            response.setResponseId(port);
            response.setResponseData(String.format("Specific exception from [%s]", appId));
        } // end of checking whether need to throw specific exception
        else if (request.getCusId().toLowerCase().contains("invalid")) {
            response.setResponseId(400);
            response.setResponseData("Generic exception");
        } // end of checking if this is an invalid customer
        else {
            response.setResponseId(200);
            response.setResponseData("Echo : " + request.getCusData());
        }

        return response;
    }// end of method index()
}
