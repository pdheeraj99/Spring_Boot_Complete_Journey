package com.security.learn.orderservice.controller;

import com.security.learn.orderservice.dto.CreateOrderRequest;
import com.security.learn.orderservice.entity.Order;
import com.security.learn.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public String createOrder(@RequestBody CreateOrderRequest request) {
        Order order = orderService.createOrder(request);
        return "Order creation process started for ID: " + order.getOrderId();
    }
}