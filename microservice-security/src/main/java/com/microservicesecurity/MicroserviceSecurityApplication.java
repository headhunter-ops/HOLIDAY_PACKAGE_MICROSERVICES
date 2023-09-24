package com.microservicesecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSecurityApplication.class, args);
	}

}
