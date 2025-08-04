package com.security.learn.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/orders")
    public String getOrderDetails() {
        return "This is the REAL response from Order Service!";
    }
}