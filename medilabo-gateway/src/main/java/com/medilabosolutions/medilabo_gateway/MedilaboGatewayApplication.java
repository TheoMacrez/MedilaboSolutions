package com.medilabosolutions.medilabo_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableWebFlux
public class MedilaboGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedilaboGatewayApplication.class, args);
	}

}
