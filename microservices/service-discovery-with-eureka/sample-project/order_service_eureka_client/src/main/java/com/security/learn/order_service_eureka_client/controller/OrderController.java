package com.security.learn.order_service_eureka_client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @GetMapping("/")
    public String getOrders() {
        // Just oka simple message return cheddam
        return "This is a response from ORDER-SERVICE!";
    }
}
