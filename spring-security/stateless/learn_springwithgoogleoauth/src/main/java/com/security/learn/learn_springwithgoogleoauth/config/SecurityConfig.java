package com.security.learn.learn_springwithgoogleoauth.config;

import com.security.learn.learn_springwithgoogleoauth.filter.JwtAuthenticationFilter;
import com.security.learn.learn_springwithgoogleoauth.handlers.OAuth2LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//  SPA lo unna "Login with Google" button, ee kindha unna special link ki point chestundi:
// Use this: If you want to manually invoke 👉 http://localhost:8080/oauth2/authorization/google

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // Inject your new handler
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    //private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth ->
                    auth
                    .requestMatchers("/").permitAll() // Home page ni andarki open chey
                    .anyRequest().authenticated() // Migatha anni pages ni lock chey
            )
            // .oauth2Login(Customizer.withDefaults()); => Direct ga login success aite mana savedRequest page ki redirect chestundi
            .oauth2Login(oauth2 -> oauth2.successHandler(oAuth2LoginSuccessHandler)) // Manam custom ga ekkadiki redirect cheyyalo cheppachu
            // Remove STATELESS for OAuth2 to work, or use IF_REQUIRED
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            );
           // .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}
