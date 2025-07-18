package com.internalslearning.authorization_server.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.UUID;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * STEP 1: OAuth2 Authorization Server Security Configuration
     *
     * This method configures security for OAuth2 authorization server endpoints.
     * When a client makes a request to /oauth2/token, this filter chain will handle it.
     *
     * Flow: Client Request → This Filter Chain → Token Generation
     */
    @Bean
    @Order(1) // High priority - this filter chain is checked first
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        // Apply default OAuth2 authorization server security configuration
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        // Configure JWT validation for resource server functionality
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }

    /**
     * STEP 2: Default Security Configuration
     *
     * This handles any requests not matched by the OAuth2 authorization server.
     * For example, if someone tries to access /some-other-endpoint
     */
    @Bean
    @Order(2) // Lower priority - checked after OAuth2 endpoints
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // All requests require authentication
                )
                .formLogin(Customizer.withDefaults()) // Enable form-based login
                .build();
    }

    /**
     * STEP 3: Client Registration Repository
     *
     * This method registers OAuth2 clients that can request tokens.
     * In our case, we're registering a client that can use Client Credentials flow.
     *
     * Flow: Client sends client_id + client_secret → Server validates against this repository
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        // Create a client registration for Client Credentials flow
        RegisteredClient clientCredentialsClient = RegisteredClient
                .withId(UUID.randomUUID().toString()) // Unique internal ID
                .clientId("demo-client") // Client ID that client will send
                .clientSecret("{noop}demo-secret") // Client secret ({noop} means no encoding)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC) // Basic Auth
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS) // Only Client Credentials
                .scope("read") // Client can request 'read' scope
                .scope("write") // Client can request 'write' scope
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(30)) // Token expires in 30 minutes
                        .build())
                .build();

        // Return in-memory repository (for production, use database)
        return new InMemoryRegisteredClientRepository(clientCredentialsClient);
    }

    /**
     * STEP 4: JWT Token Signing Configuration
     *
     * This method creates the cryptographic keys used to sign JWT tokens.
     * The private key signs the token, public key verifies it.
     *
     * Flow: Token Generation → Sign with Private Key → Client receives signed JWT
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        // Generate RSA key pair for JWT signing
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        // Create RSA key for JWT signing
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString()) // Unique key ID
                .build();

        // Create JWK Set and return as immutable source
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    /**
     * STEP 5: RSA Key Pair Generation
     *
     * This method generates a 2048-bit RSA key pair for JWT signing.
     * In production, you should load pre-generated keys from secure storage.
     *
     * RSA Keys Explained:
     * - Private Key: Used by Authorization Server to SIGN tokens
     * - Public Key: Used by Resource Server to VERIFY tokens
     */
    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // 2048-bit key size for security
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to generate RSA key pair", ex);
        }
        return keyPair;
    }

    /**
     * STEP 6: JWT Decoder Configuration
     *
     * This creates a JWT decoder that can validate tokens issued by this server.
     * The Resource Server will use this to validate incoming tokens.
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    /**
     * STEP 7: Authorization Server Settings
     *
     * This configures the OAuth2 authorization server settings like issuer URL.
     * The issuer URL is included in JWT tokens and used by resource servers.
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .issuer("http://localhost:9000") // This server's URL
                .build();
    }
}
