package com.security.learn.orderservicedirector.controller;

import com.security.learn.common.dto.CreateOrderRequest;
import com.security.learn.orderservicedirector.entity.Order;
import com.security.learn.orderservicedirector.orchestrator.SagaOrchestrator;
import com.security.learn.orderservicedirector.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired private SagaOrchestrator sagaOrchestrator;

    @PostMapping("/orders")
    public String createOrder(@RequestBody CreateOrderRequest request) {
        // 1. Create the order record in the database
        Order order = orderService.createOrder(request);
        // 2. Start the saga for this order
        sagaOrchestrator.startSaga(order);
        return "Order creation process started for ID: " + order.getOrderId();
    }
}