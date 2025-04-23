package com.medilabosolutions.medilabo_notes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration de la sécurité de l'API utilisant OAuth2 et JWT.
 */
@Configuration
public class SecurityConfig {

    /**
     * Configure la chaîne de filtres de sécurité.
     *
     * @param http l'objet HttpSecurity à configurer
     * @return la chaîne de filtres de sécurité
     * @throws Exception en cas d'erreur de configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF désactivé pour une API REST sécurisée via OAuth2
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt()
                );

        return http.build();
    }
}
