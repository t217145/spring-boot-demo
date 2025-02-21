package com.cyrus822.demo.mq.rmq;

import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Component
public class RMQRunner{

    @RabbitListener(queues = "${rmq.queue.name}")
    public void processMessage(String message) {
        System.out.println("Received Message: " + message);
    }

}