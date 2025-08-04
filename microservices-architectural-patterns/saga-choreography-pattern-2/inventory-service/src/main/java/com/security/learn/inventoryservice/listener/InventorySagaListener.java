package com.security.learn.inventoryservice.listener;

import com.security.learn.common.dto.InventoryFailedEvent;
import com.security.learn.inventoryservice.config.RabbitMQConfig;
import com.security.learn.common.dto.InventorySuccessEvent;
import com.security.learn.common.dto.PaymentSuccessEvent;
import com.security.learn.inventoryservice.repo.InventoryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventorySagaListener {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = RabbitMQConfig.INVENTORY_QUEUE)
    public void onPaymentSuccess(PaymentSuccessEvent event) {
        System.out.println("Received PAYMENT_SUCCESS_KEY for Order ID: " + event.getOrderId());

        inventoryRepository.findById(event.getProductId()).ifPresent(inventory -> {
            // ================== NEW FAILURE LOGIC ==================
            if (inventory.getAvailableStock() >= event.getQuantity()) {
                // HAPPY PATH: Stock is available
                inventory.setAvailableStock(inventory.getAvailableStock() - event.getQuantity());
                inventoryRepository.save(inventory);

                InventorySuccessEvent nextEvent = new InventorySuccessEvent(event.getOrderId());
                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.INVENTORY_SUCCESS_KEY, nextEvent);
                System.out.println("Inventory RESERVED. Published INVENTORY_SUCCESS_KEY for Order ID: " + event.getOrderId());
            } else {
                // SAD PATH: Out of stock
                System.out.println("Inventory FAILED (out of stock) for Order ID: " + event.getOrderId());

                InventoryFailedEvent failureEvent = new InventoryFailedEvent(
                        event.getOrderId(),
                        event.getProductId(),
                        event.getQuantity(),
                        "Insufficient stock"
                );
                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.INVENTORY_FAILURE_KEY, failureEvent);
                System.out.println("Published INVENTORY_FAILURE_KEY for Order ID: " + event.getOrderId());
            }
        });
    }
}