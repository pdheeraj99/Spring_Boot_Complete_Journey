package com.security.learn.cqrsorderservice.service;

import com.security.learn.cqrsorderservice.dto.CreateOrderCommand;
import com.security.learn.cqrsorderservice.entity.Order;
import com.security.learn.cqrsorderservice.repo.OrderCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderCommandService {
    @Autowired
    private OrderCommandRepository orderCommandRepository;

    public String handleCreateOrderCommand(CreateOrderCommand command) {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setProductId(command.getProductId());
        order.setQuantity(command.getQuantity());
        order.setPrice(command.getPrice());
        order.setShippingAddress(command.getShippingAddress());
        order.setStatus("CREATED");

        orderCommandRepository.save(order);

        System.out.println("COMMAND: Saved new order with ID: " + order.getOrderId());
        return order.getOrderId();
    }
}