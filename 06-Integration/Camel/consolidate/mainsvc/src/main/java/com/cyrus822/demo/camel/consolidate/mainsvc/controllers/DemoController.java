package com.cyrus822.demo.camel.consolidate.mainsvc.controllers;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.SimpleRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cyrus822.demo.camel.consolidate.mainsvc.processors.DemoBean;
import com.cyrus822.demo.camel.consolidate.mainsvc.processors.DemoProcessor;

@RestController
public class DemoController {

    @Autowired
    private ProducerTemplate tpl;

    @Autowired
    private DemoBean dBean;

    @Autowired
    private DemoProcessor dPro;
    
    @GetMapping("/")
    public String index() throws Exception{

        SimpleRegistry simpleRegistry = new SimpleRegistry(); // this class is just a mapper
        simpleRegistry.bind("DemoBean", dBean);
        simpleRegistry.bind("DemoProcessor", dPro);
        CamelContext camelContext = new DefaultCamelContext(simpleRegistry);

        camelContext.start();

        tpl.start();
        String rtn = tpl.requestBody("direct:start11", null, String.class);
        tpl.stop();

        camelContext.close();
        return rtn;
    }
}
