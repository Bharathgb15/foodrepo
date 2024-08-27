package com.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class CustomermoduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomermoduleApplication.class, args);
		
	}

	@Bean
	public RestTemplate getrestRestTemplate() {
		return new RestTemplate();
	}
}
 