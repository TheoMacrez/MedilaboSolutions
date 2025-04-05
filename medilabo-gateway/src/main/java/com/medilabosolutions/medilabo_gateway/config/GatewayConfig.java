package com.medilabosolutions.medilabo_gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;



@Configuration
@EnableWebFlux // active le mode réactif
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("medilabo-patient", r -> r
                        .path("/patients/**")  // correspond aux requêtes vers "/patients/*"
                        .uri("http://localhost:8081")  // redirige vers le backend
                )
                .build();
    }

    // Optionnel: ajouter un filtre personnalisé (par exemple, un log de chaque requête)
    @Bean
    public GatewayFilter customFilter() {
        return (exchange, chain) -> {
            System.out.println("Request path: " + exchange.getRequest().getPath());
            return chain.filter(exchange);
        };
    }
}

