package com.cyrus822.demo.camel.consolidate.mainsvc.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DemoBean {

    private Logger logger = LoggerFactory.getLogger(DemoBean.class);

    public String demo(){
        logger.info("Hello");
        return "hello";
    }
}
