package com.medilabosolutions.medilabo_notes.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
public class TestSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // désactiver CSRF pour les tests
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                ); // toutes les requêtes sont autorisées sans authentification
        return http.build();
    }
}

