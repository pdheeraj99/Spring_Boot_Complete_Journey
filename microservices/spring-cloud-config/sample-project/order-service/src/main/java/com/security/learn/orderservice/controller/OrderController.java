package com.security.learn.orderservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// HIT IN TERMINAL : curl -X POST http://localhost:8081/actuator/refresh ( after changing configuration in GIT )
@RestController
// <-- The magic to enable refresh on this bean.Manam refresh endpoint hit chesinappudu kotha bean create avtundi
// *** Also refresh endpoint ni manam manual ga expose cheyyali from actuators ***
@RefreshScope
public class OrderController {
    // Inject the property from our Git file
    @Value("${order.message}")
    private String messageFromGit;

    @Value("${server.port}")
    private String ownMessageFromGit;

    @GetMapping("/message")
    public String getMessage() {
        return this.messageFromGit + " " + ownMessageFromGit;
    }
}
