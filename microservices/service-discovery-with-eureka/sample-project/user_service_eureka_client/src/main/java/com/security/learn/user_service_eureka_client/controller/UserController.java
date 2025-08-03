package com.security.learn.user_service_eureka_client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String getOrder() {
        // Chudu, ikkada manam 'localhost:8081' ani ivvaledu. Service name icham!
        return "Getting orders from USER-SERVICE";
    }

    @GetMapping("/get-order-details")
    public String getOrderDetails() {
        // Chudu, ikkada manam 'localhost:8081' ani ivvaledu. Service name icham!
        String orderResponse = restTemplate.getForObject("http://ORDER-SERVICE/orders/", String.class);

        return "I am USER-SERVICE, and I got this response from ORDER-SERVICE: [" + orderResponse + "]";
    }
}
