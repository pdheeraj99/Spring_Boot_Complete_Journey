package com.internalslearning.basicinternalsunderstanding.autoconfigure;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;

@Configuration
@Slf4j
public class SecurityAnalysisConfig {

    @PostConstruct
    public void analyzeSecurityAutoConfiguration() {
        log.info("=== SECURITY AUTO-CONFIGURATION ANALYSIS ===");

        // Check if the trigger class exists on classpath
        try {
            Class.forName("org.springframework.security.authentication.DefaultAuthenticationEventPublisher");
            log.info("✅ DefaultAuthenticationEventPublisher found on classpath - @ConditionalOnClass SATISFIED");
        } catch (ClassNotFoundException e) {
            log.info("❌ DefaultAuthenticationEventPublisher NOT found - @ConditionalOnClass FAILED");
        }

        // This will help you see what beans are created
        log.info("Checking for AuthenticationManager and AuthenticationProvider beans...");
    }

    // Add this bean to see the condition in action
    @Bean
    @ConditionalOnMissingBean({AuthenticationManager.class, AuthenticationProvider.class})
    public String customSecurityAnalyzer() {
        return "Auto-configuration conditions were met!";
    }
}