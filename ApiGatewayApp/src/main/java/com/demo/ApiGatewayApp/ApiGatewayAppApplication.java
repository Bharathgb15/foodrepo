package com.demo.ApiGatewayApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayAppApplication.class, args);
	}

	@Bean
	public RouteLocator configureRoute(RouteLocatorBuilder builder) {
		return builder.routes().route("FOODMODULE", r -> r.path("/app1/**").uri("lb://FOODMODULE"))
				.route("CUSTOMERMODULE", r -> r.path("/app2/**").uri("lb://CUSTOMERMODULE")).build();
	}

}
