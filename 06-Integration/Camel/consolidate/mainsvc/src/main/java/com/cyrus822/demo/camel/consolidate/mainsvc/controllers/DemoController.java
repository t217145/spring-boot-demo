package com.cyrus822.demo.camel.consolidate.mainsvc.controllers;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cyrus822.demo.camel.consolidate.mainsvc.domains.DemoRequest;

@RestController
public class DemoController {

    @Autowired
    private ProducerTemplate tpl;

    @PostMapping("/")
    public String index(@RequestBody DemoRequest req) throws Exception {
        tpl.start();
        String rtn = tpl.requestBody("direct:start", req, String.class);
        tpl.stop();

        return rtn;
    }
}
