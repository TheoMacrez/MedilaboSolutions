package com.medilabosolutions.medilabo_patient.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Désactive CSRF si nécessaire pour faciliter le développement
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/auth/login").permitAll() // Autoriser certains chemins sans authentification
//                        .anyRequest().authenticated()
//                        // Tout le reste nécessite une authentification
//                )
//                .formLogin().disable() // Désactive le formulaire de login par défaut de Spring Security
//                .logout().permitAll(); // Autorise la déconnexion sans authentification;
//        return http.build();
//    }
}
