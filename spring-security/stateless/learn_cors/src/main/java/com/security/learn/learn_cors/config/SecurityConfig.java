package com.security.learn.learn_cors.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.swing.*;
import java.util.Arrays;

//The line `    .cors(Customizer.withDefaults())    ` acts like a switch that turns on the CORS feature,
// and Spring then automatically uses your custom CorsConfigurationSource bean to get the rules.

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // This line tells Spring Security to enable CORS and look for a bean
                // named 'corsConfigurationSource' for the configuration.
                .cors(Customizer.withDefaults())

                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        // First, let's create a new CORS configuration object.
        // Ekkada manam mana custom rules define chestam.
        CorsConfiguration configuration = new CorsConfiguration();

        // Here, we tell the browser: "Only allow requests coming from these origins."
        // Ekkada manam mana React app URL (http://localhost:3000) ni specify chestunnam.
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));

        // We are specifying which HTTP methods are allowed from the frontend.
        // Ee HTTP methods ni matrame allow cheyamani cheptunnam.
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // We are giving permission for the frontend to send these specific headers.
        // Frontend ee headers pampinchadaniki permission istunnam.
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        // If this is 'true', credentials like cookies or Authorization tokens can be sent.
        // Idi 'true' pedithe, frontend nunchi credentials pampochu.
        // *** False pettina pampochu but follow the industry practise
        configuration.setAllowCredentials(true);

        // We are creating a source to apply these rules to our application's URLs.
        // Ee rules ni URLs ki apply cheyadaniki oka source create chestunnam.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Apply the rules we created to ALL URLs ("/**") in our application.
        // Manam create chesina rules ni, mana application lo unna anni URLs ("/**") ki apply chey.
        source.registerCorsConfiguration("/**", configuration);

        // Finally, we return this source object. Spring Security will use it automatically.
        return source;
    }
}