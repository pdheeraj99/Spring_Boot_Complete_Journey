package com.security.learn.user_service_eureka_client.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// -> Ee RestTemplate tho http://ORDER-SERVICE/orders lanti URL call chesinappudu, ORDER-SERVICE anedi normal website kadu.
// -> Daani address ni Eureka lo vethiki, correct IP:Port tho replace chesi call cheyi

@Configuration
public class AppConfig {
    @Bean
    @LoadBalanced // <-- IDE ASALU MAGIC! Ee annotation chala important.
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
