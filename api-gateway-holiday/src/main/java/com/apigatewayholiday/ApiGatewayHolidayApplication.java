package com.apigatewayholiday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayHolidayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayHolidayApplication.class, args);
	}

}
