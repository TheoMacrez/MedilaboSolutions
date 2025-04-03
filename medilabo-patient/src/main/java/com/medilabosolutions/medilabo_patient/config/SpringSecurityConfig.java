package com.medilabosolutions.medilabo_patient.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactive la protection CSRF pour les tests
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/patients/**").permitAll() // Permet l'accès sans authentification
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}

