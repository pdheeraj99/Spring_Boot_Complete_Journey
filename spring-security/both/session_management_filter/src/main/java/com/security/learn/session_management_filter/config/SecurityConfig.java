package com.security.learn.session_management_filter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        // ... other configurations like cors(), csrf() ...
        .sessionManagement(session -> session
                // Ee setting Spring Security ki cheptundi: "Never create a session (HttpSession)
                // for any user. Be completely stateless.
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        return http.build();
    }

}
