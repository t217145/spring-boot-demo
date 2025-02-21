package com.cyrus822.demo.mq.rmq.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class RMQController {

    @Value("${rmq.queue.name}")
    private String queueName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String send(String message) {

        rabbitTemplate.convertAndSend(queueName, message);
        return String.format("%s sent to the queue", message);
    }    
}