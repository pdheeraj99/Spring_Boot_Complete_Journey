package com.security.learn.learn_usernamepwdauthfilter.config;

import com.security.learn.learn_usernamepwdauthfilter.custom_implementations.OurCustomUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final OurCustomUserDetailsService ourCustomUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(ourCustomUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for now to test with Postman
                .authorizeHttpRequests(auth -> auth
                        // Allow anyone to access registration/login endpoints
                        .requestMatchers("/api/auth/**").permitAll()
                        // Any other request needs to be authenticated
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginProcessingUrl("/login") // Explicitly set the login URL
                        .successHandler((request, response, authentication) -> {
                            // On success, send a 200 OK with a clear message
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.getWriter().write("Login Successful!");
                        })
                        .failureHandler((request, response, exception) -> {
                            // On failure, send a 401 Unauthorized with the error message
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.getWriter().write("Login Failed: " + exception.getMessage());
                        })
                        .permitAll()
                );

        return http.build();
    }
}
