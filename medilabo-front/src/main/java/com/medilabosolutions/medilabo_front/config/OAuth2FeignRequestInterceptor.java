package com.medilabosolutions.medilabo_front.config;


import feign.RequestInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import org.springframework.security.core.context.SecurityContextHolder;


/**
 * Configuration d'un interceptor Feign pour ajouter le token d'accès OAuth2 dans les requêtes HTTP sortantes.
 */
@Configuration
public class OAuth2FeignRequestInterceptor {

    private final OAuth2AuthorizedClientService authorizedClientService;

    public OAuth2FeignRequestInterceptor(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    /**
     * Bean Feign qui injecte le header Authorization dans toutes les requêtes sortantes.
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication instanceof OAuth2AuthenticationToken oauthToken) {
                String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
                OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                        clientRegistrationId,
                        oauthToken.getName()
                );
                if (client != null) {
                    OAuth2AccessToken accessToken = client.getAccessToken();
                    if (accessToken != null) {
                        requestTemplate.header("Authorization", "Bearer " + accessToken.getTokenValue());
                    }
                }
            }
        };
    }
}

