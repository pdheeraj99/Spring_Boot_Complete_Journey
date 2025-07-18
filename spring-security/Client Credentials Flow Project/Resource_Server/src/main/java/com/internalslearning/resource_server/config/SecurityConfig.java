package com.internalslearning.resource_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // Enable method-level security
public class SecurityConfig {

    /**
     * STEP 1: Resource Server Security Configuration
     *
     * This method configures how the Resource Server handles incoming requests.
     * When a client sends a request with a JWT token, this configuration
     * determines how to validate and authorize the request.
     *
     * Flow: Client Request with JWT → JWT Validation → Scope Check → API Access
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints - no authentication required
                        .requestMatchers("/api/public/**").permitAll()

                        // Protected endpoints - require specific scopes
                        .requestMatchers("/api/users").hasAuthority("SCOPE_read")
                        .requestMatchers("/api/admin/**").hasAuthority("SCOPE_write")

                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                // Configure OAuth2 Resource Server with JWT
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                // Use custom JWT authentication converter
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                )
                .build();
    }

    /**
     * STEP 2: JWT Authentication Converter
     *
     * This method configures how JWT tokens are converted to Spring Security authorities.
     * The converter extracts scopes from the JWT and converts them to authorities
     * that can be used in security expressions.
     *
     * JWT Token Structure:
     * {
     *   "sub": "demo-client",
     *   "scope": "read write",
     *   "iss": "http://localhost:9000",
     *   "exp": 1234567890
     * }
     *
     * After conversion, authorities will be: ["SCOPE_read", "SCOPE_write"]
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        // Convert JWT scopes to Spring Security authorities
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthorityPrefix("SCOPE_"); // Prefix for authorities
        authoritiesConverter.setAuthoritiesClaimName("scope"); // JWT claim name containing scopes

        // Create JWT authentication converter
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return converter;
    }
}
