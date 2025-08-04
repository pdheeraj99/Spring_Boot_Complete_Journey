package com.security.learn.orderservice.service;

import com.security.learn.orderservice.dto.CreateOrderRequest;
import com.security.learn.orderservice.dto.InventorySuccessEvent;
import com.security.learn.orderservice.dto.OrderCreatedEvent;
import com.security.learn.orderservice.entity.Order;
import com.security.learn.orderservice.enums.OrderStatus;
import com.security.learn.orderservice.rabbitconfig.RabbitMQConfig;
import com.security.learn.orderservice.repo.OrderRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Order createOrder(CreateOrderRequest request) {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setAmount(request.getAmount());
        order.setStatus(OrderStatus.PENDING);
        orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(order.getOrderId(), order.getProductId(), order.getQuantity(), order.getAmount());

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ORDER_CREATED_KEY, event);
        System.out.println("Published ORDER_CREATED_KEY for Order ID: " + order.getOrderId());
        return order;
    }

    // ADD THIS NEW METHOD
    public void confirmOrder(InventorySuccessEvent event) {
        orderRepository.findById(event.getOrderId()).ifPresent(order -> {
            order.setStatus(OrderStatus.CONFIRMED);
            orderRepository.save(order);
            System.out.println("SAGA COMPLETED: Order " + order.getOrderId() + " is now CONFIRMED.");
        });
    }
}