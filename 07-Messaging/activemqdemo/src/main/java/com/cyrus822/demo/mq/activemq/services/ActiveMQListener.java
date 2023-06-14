package com.cyrus822.demo.mq.activemq.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "receive.mode", havingValue = "svc")
public class ActiveMQListener {
    Logger logger = LoggerFactory.getLogger(ActiveMQListener.class);

    @JmsListener(destination = "${demo.queue}")
    public void receive(String message) {
        logger.info("receive from listener {} success", message);
    }
}
