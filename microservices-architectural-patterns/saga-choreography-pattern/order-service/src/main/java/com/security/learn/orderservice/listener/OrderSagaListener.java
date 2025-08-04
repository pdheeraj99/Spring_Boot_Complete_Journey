package com.security.learn.orderservice.listener;

import com.security.learn.orderservice.dto.InventorySuccessEvent;
import com.security.learn.orderservice.rabbitconfig.RabbitMQConfig;
import com.security.learn.orderservice.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderSagaListener {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE)
    public void onInventorySuccess(InventorySuccessEvent event) {
        System.out.println("Received INVENTORY_SUCCESS_KEY for Order ID: " + event.getOrderId());
        orderService.confirmOrder(event);
    }
}