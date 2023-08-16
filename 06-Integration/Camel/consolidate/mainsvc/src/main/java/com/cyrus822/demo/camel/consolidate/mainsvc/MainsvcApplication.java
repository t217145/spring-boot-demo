package com.cyrus822.demo.camel.consolidate.mainsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.cyrus822.demo.camel.consolidate.mainsvc.*")
public class MainsvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainsvcApplication.class, args);
	}

}
