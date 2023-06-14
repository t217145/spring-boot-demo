package com.cyrus822.demo.mq.activemq.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.jms.JMSException;
import jakarta.jms.Message;

@RestController
public class ActiveMQController {

    Logger logger = LoggerFactory.getLogger(ActiveMQController.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/send/{dest}")
    public String send(@PathVariable String dest, @RequestParam String msg) {
        jmsTemplate.convertAndSend(dest, msg);
        logger.info("send {} success", msg);
        return String.format("send %s success", msg);
    }

    @GetMapping("/receive/{dest}")
    public String receiver(@PathVariable String dest) {
        String rtn = "";
        try {
            Message msg = jmsTemplate.receive(dest);
            logger.info("receive {} success", msg);
            if (msg != null) {
                rtn = msg.getBody(String.class);
            } else {
                logger.warn("receive null from {}", dest);
            }
        } catch (JMSException e) {
            logger.error("error : ", e);
        }
        return rtn;
    }
}
