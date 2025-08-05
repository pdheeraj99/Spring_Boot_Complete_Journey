package com.security.learn.cqrsorderservice.controller;

import com.security.learn.cqrsorderservice.dto.CreateOrderCommand;
import com.security.learn.cqrsorderservice.service.OrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/commands") // We group all command endpoints together
public class OrderCommandController {

    @Autowired
    private OrderCommandService orderCommandService;

    @PostMapping("/create")
    public String createOrder(@RequestBody CreateOrderCommand command) {
        return orderCommandService.handleCreateOrderCommand(command);
    }
}