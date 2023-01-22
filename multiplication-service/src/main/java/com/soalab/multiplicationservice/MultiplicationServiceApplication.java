package com.soalab.multiplicationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MultiplicationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiplicationServiceApplication.class, args);
	}

}
