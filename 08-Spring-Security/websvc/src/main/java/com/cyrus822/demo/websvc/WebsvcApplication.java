package com.cyrus822.demo.websvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.cyrus822.demo.common.domains")
public class WebsvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsvcApplication.class, args);
	}

}
