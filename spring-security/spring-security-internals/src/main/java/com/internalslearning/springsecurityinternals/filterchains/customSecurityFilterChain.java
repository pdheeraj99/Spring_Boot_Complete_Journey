package com.internalslearning.springsecurityinternals.filterchains;

import com.internalslearning.springsecurityinternals.filters.CustomHeaderFilter;
import com.internalslearning.springsecurityinternals.filters.LoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity(debug = true)
public class customSecurityFilterChain {

    @Bean
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**") // Only applies to /api/**
                .addFilterAfter(new LoggingFilter(), HeaderWriterFilter.class);
        return http.build();
    }

    @Bean
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/") // Only applies to /api/**
                .addFilterBefore(new LoggingFilter(), HeaderWriterFilter.class);
        return http.build();
    }

}
