package com.security.learn.orderservicedirector.service;

import com.security.learn.common.dto.CreateOrderRequest;
import com.security.learn.orderservicedirector.entity.Order;
import com.security.learn.orderservicedirector.enums.OrderStatus;
import com.security.learn.orderservicedirector.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(CreateOrderRequest request) {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setAmount(request.getAmount());
        order.setStatus(OrderStatus.PENDING);
        return orderRepository.save(order);
    }

    public void confirmOrder(String orderId) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setStatus(OrderStatus.CONFIRMED);
            orderRepository.save(order);
            System.out.println("SAGA COMPLETED: Order " + orderId + " is CONFIRMED.");
        });
    }

    public void cancelOrder(String orderId) {
        orderRepository.findById(orderId).ifPresent(order -> {
            if (order.getStatus() == OrderStatus.PENDING) {
                order.setStatus(OrderStatus.CANCELLED);
                orderRepository.save(order);
                System.out.println("SAGA ROLLED BACK: Order " + orderId + " is CANCELLED.");
            }
        });
    }
}