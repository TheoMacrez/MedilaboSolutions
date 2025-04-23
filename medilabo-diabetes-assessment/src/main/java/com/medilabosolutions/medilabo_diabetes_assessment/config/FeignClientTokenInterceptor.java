package com.medilabosolutions.medilabo_diabetes_assessment.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

/**
 * Intercepteur Feign ajoutant automatiquement le token JWT
 * d'authentification dans les appels sortants.
 */
@Configuration
public class FeignClientTokenInterceptor {


    /**
     * Configure l'intercepteur pour insérer l'en-tête Authorization Bearer.
     *
     * @return {@link RequestInterceptor}
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                var authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication instanceof JwtAuthenticationToken jwtAuthToken) {
                    String tokenValue = jwtAuthToken.getToken().getTokenValue();
                    requestTemplate.header("Authorization", "Bearer " + tokenValue);
                }
            }
        };
    }
}

