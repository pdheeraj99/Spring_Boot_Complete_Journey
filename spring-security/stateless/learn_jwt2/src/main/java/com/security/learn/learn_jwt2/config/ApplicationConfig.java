package com.security.learn.learn_jwt2.config;

import com.security.learn.learn_jwt2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository userRepository;

    // This bean is the "clerk" that knows how to find a user by their email.
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // This bean is the "specialist" that uses the UserDetailsService and PasswordEncoder
    // to verify a user's credentials.

    // --- NO NEED OF BELOW

    //    @Bean
    //    public AuthenticationProvider authenticationProvider() {
    //        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    //        authProvider.setUserDetailsService(userDetailsService());
    //        authProvider.setPasswordEncoder(passwordEncoder());
    //        return authProvider;
    //    }

    // This bean is the main manager for the authentication process.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // This bean is the "password checker" that uses the BCrypt algorithm.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
