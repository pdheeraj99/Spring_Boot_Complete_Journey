package com.internalslearning.client_application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OAuth2ClientConfig {

    /**
     * STEP 1: OAuth2 Authorized Client Manager
     *
     * This manager handles OAuth2 client credentials flow automatically.
     * It manages token acquisition, caching, and refresh.
     *
     * Flow: Request → Check for valid token → Request new token if needed → Return token
     */
    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientService authorizedClientService) {

        return new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                clientRegistrationRepository,
                authorizedClientService
        );
    }

    /**
     * STEP 2: WebClient with OAuth2 Support
     *
     * This WebClient automatically adds OAuth2 access tokens to requests.
     * The filter function handles token acquisition and attachment.
     *
     * Flow: HTTP Request → Get Access Token → Add to Authorization Header → Send Request
     */
    @Bean
    public WebClient webClient(OAuth2AuthorizedClientManager authorizedClientManager) {
        // Create OAuth2 filter function
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);

        // Set default client registration ID
        oauth2Client.setDefaultClientRegistrationId("demo-client");

        // Build WebClient with OAuth2 filter
        return WebClient.builder()
                .filter(oauth2Client)
                .build();
    }
}
