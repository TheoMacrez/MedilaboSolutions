package com.medilabosolutions.medilabo_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = "com.medilabosolutions.medilabo_gateway.client")
public class MedilaboGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedilaboGatewayApplication.class, args);
	}

}
