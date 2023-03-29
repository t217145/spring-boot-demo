package com.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RefreshScope
@RestController
public class ClientApplication {

    @Value("${service-a.value.name}")
    private String name;

    @Value("${service-a.value.age}")
    private int age;

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @GetMapping("/test")
    public String test(){
        return String.format("My name %s. I am %d year(s) old.", name, age);
    }
}
