package com.security.learn.inventoryservice.listener;

import com.security.learn.inventoryservice.dto.InventorySuccessEvent;
import com.security.learn.inventoryservice.dto.PaymentSuccessEvent;
import com.security.learn.inventoryservice.rabbitconfig.RabbitMQConfig;
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

        // For the happy path, we assume stock is available
        inventoryRepository.findById(event.getProductId()).ifPresent(inventory -> {
            inventory.setAvailableStock(inventory.getAvailableStock() - event.getQuantity());
            inventoryRepository.save(inventory);

            InventorySuccessEvent nextEvent = new InventorySuccessEvent(event.getOrderId());
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.INVENTORY_SUCCESS_KEY, nextEvent);
            System.out.println("Inventory RESERVED. Published INVENTORY_SUCCESS_KEY for Order ID: " + event.getOrderId());
        });
    }
}