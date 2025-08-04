package com.security.learn.paymentservice.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.learn.common.dto.InventoryFailedEvent;
import com.security.learn.paymentservice.config.RabbitMQConfig;
import com.security.learn.common.dto.OrderCreatedEvent;
import com.security.learn.paymentservice.service.PaymentService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentSagaListener {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.PAYMENT_QUEUE)
    public void onSagaEvent(Message message) {
        try {
            String routingKey = message.getMessageProperties().getReceivedRoutingKey();
            String jsonBody = new String(message.getBody());
            System.out.println("Payment-Service received event with key: " + routingKey);

            if (RabbitMQConfig.ORDER_CREATED_KEY.equals(routingKey)) {
                OrderCreatedEvent event = objectMapper.readValue(jsonBody, OrderCreatedEvent.class);
                paymentService.processPayment(event);
            } else if (RabbitMQConfig.INVENTORY_FAILURE_KEY.equals(routingKey)) {
                // ADD THIS PART
                InventoryFailedEvent event = objectMapper.readValue(jsonBody, InventoryFailedEvent.class);
                paymentService.refundPayment(event);
            }
        } catch (Exception e) {
            System.err.println("Error processing message in PaymentSagaListener: " + e.getMessage());
        }
    }
}