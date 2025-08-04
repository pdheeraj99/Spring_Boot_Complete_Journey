package com.security.learn.orderservice.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.learn.common.dto.InventoryFailedEvent;
import com.security.learn.orderservice.config.RabbitMQConfig;
import com.security.learn.common.dto.InventorySuccessEvent;
import com.security.learn.common.dto.PaymentFailedEvent;
import com.security.learn.orderservice.service.OrderService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderSagaListener {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE)
    public void onSagaEvent(Message message) {
        try {
            String routingKey = message.getMessageProperties().getReceivedRoutingKey();
            String jsonBody = new String(message.getBody());
            System.out.println("Order-Service received event with key: " + routingKey);

            if (RabbitMQConfig.INVENTORY_SUCCESS_KEY.equals(routingKey)) {
                InventorySuccessEvent event = objectMapper.readValue(jsonBody, InventorySuccessEvent.class);
                orderService.confirmOrder(event);
            } else if (RabbitMQConfig.PAYMENT_FAILURE_KEY.equals(routingKey)) {
                PaymentFailedEvent event = objectMapper.readValue(jsonBody, PaymentFailedEvent.class);
                orderService.cancelOrder(event);
            } else if (RabbitMQConfig.INVENTORY_FAILURE_KEY.equals(routingKey)) {
                // ADD THIS PART
                InventoryFailedEvent event = objectMapper.readValue(jsonBody, InventoryFailedEvent.class);
                orderService.cancelOrder(event);
                // You will need to update the cancelOrder method to accept this event type or a common interface
            }
        } catch (Exception e) {
            System.err.println("Error processing message in OrderSagaListener: " + e.getMessage());
        }
    }
}