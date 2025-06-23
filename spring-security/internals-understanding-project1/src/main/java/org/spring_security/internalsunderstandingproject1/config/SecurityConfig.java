package org.spring_security.internalsunderstandingproject1.config;

import org.spring_security.internalsunderstandingproject1.filters.CustomLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final CustomLoggingFilter customLoggingFilter;

    public SecurityConfig(CustomLoggingFilter customLoggingFilter) {
        this.customLoggingFilter = customLoggingFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Enable form login to include UsernamePasswordAuthenticationFilter
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll
                )
                .addFilterBefore(customLoggingFilter, UsernamePasswordAuthenticationFilter.class); // Add custom filter

        return http.build();
    }
}