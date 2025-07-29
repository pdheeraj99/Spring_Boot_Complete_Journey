package com.security.learn.learn_jwt2.config;

import com.security.learn.learn_jwt2.filter.AuthRateLimitingFilter;
import com.security.learn.learn_jwt2.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // We no longer need to inject the AuthenticationProvider here
    // private final AuthenticationProvider authenticationProvider;

    private final JwtAuthenticationFilter jwtAuthFilter; // Inject our filter
    private final AuthRateLimitingFilter rateLimitFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Allow registration/login
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .anyRequest().authenticated() // Protect all other requests
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // We want to be stateless
                )
                // .authenticationProvider(authenticationProvider); // Use our custom provider
                .addFilterBefore(rateLimitFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                // --- ADD THIS HEADERS CONFIGURATION BLOCK ---
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::deny) // Prevent Clickjacking
                        .contentSecurityPolicy(csp -> csp
                                .policyDirectives("script-src 'self'") // Prevent XSS
                        )
                );

                // Production Environment : will handle the HTTPS enforcement before the request ever reaches our application
                // The Spring team deprecated this method because enforcing HTTPS is now considered the job of your infrastructure (like a load balancer, reverse proxy, or cloud service), not the application itself.
                // In a professional production environment, you would configure your web server (like NGINX) or your cloud load balancer to automatically redirect all http traffic to https

                // .requiresChannel(channel -> channel
                //        .anyRequest().requiresSecure()
                //);

        return http.build();
    }

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // --- FIX THIS LINE ---
        // Change the port from 3000 to 5173
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
