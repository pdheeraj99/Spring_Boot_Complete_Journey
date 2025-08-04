package com.security.learn.userservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/call-orders")
    @CircuitBreaker(name = "orderServiceBreaker", fallbackMethod = "orderServiceFallback")
    public String callOrderService() {
        logger.info("Making a call to order-service...");
        // We use the direct URL since we are not using Eureka
        String response = restTemplate.getForObject("http://localhost:8081/orders", String.class);
        return "SUCCESS:" + response;
    }

    public String orderServiceFallback(Throwable t) {
        logger.error("Fallback called. Error: {}", t.getMessage());
        return "FALLBACK: The order service is down. Please try again later.";
    }
}
