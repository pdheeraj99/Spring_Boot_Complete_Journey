package com.security.learn.inventoryserviceactor.service;

import com.security.learn.common.dto.InventoryReply;
import com.security.learn.common.dto.InventoryRequest;
import com.security.learn.inventoryserviceactor.config.RabbitMQConfig;
import com.security.learn.inventoryserviceactor.enums.ReplyStatus;
import com.security.learn.inventoryserviceactor.repo.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired private RabbitTemplate rabbitTemplate;

    @Transactional
    public void reserveInventory(InventoryRequest request) {
        System.out.println("\n--- [INVENTORY] Starting reserveInventory Transaction for Order ID: " + request.getOrderId() + " ---");

        inventoryRepository.findById(request.getProductId()).ifPresentOrElse(inventory -> {
                    System.out.println("[INVENTORY] Found product: " + inventory.getProductId() + " with stock: " + inventory.getAvailableStock());
                    InventoryReply reply;

                    if (inventory.getAvailableStock() >= request.getQuantity()) {
                        inventory.setAvailableStock(inventory.getAvailableStock() - request.getQuantity());

                        System.out.println("[INVENTORY] Attempting to save inventory with new stock: " + inventory.getAvailableStock());
                        inventoryRepository.save(inventory);
                        System.out.println("[INVENTORY] Inventory save command issued. DB should be updated if transaction commits.");

                        reply = new InventoryReply(request.getOrderId(), ReplyStatus.SUCCESS, null);

                        try {
                            System.out.println("[INVENTORY] Attempting to send SUCCESS reply to RabbitMQ...");
                            rabbitTemplate.convertAndSend(RabbitMQConfig.SAGA_REPLY_QUEUE, reply);
                            System.out.println("[INVENTORY] SUCCESS reply sent to RabbitMQ.");
                        } catch (Exception e) {
                            System.err.println("!!! [INVENTORY] CRITICAL: FAILED to send reply to RabbitMQ. This will cause a transaction rollback. !!!");
                            e.printStackTrace();
                            // Re-throw the exception to ensure the transaction rolls back
                            throw new RuntimeException(e);
                        }

                    } else {
                        reply = new InventoryReply(request.getOrderId(), ReplyStatus.FAILURE, "Insufficient stock");
                        System.out.println("[INVENTORY] FAILED (out of stock). Sending FAILURE reply.");
                        rabbitTemplate.convertAndSend(RabbitMQConfig.SAGA_REPLY_QUEUE, reply);
                    }
                },
                () -> {
                    System.err.println("!!! [INVENTORY] CRITICAL: Product ID not found: " + request.getProductId() + " !!!");
                    InventoryReply reply = new InventoryReply(request.getOrderId(), ReplyStatus.FAILURE, "Product not found");
                    rabbitTemplate.convertAndSend(RabbitMQConfig.SAGA_REPLY_QUEUE, reply);
                });

        System.out.println("--- [INVENTORY] Finishing reserveInventory Transaction for Order ID: " + request.getOrderId() + " ---\n");
    }


    @Transactional
    public void releaseInventory(InventoryRequest request) {
        inventoryRepository.findById(request.getProductId()).ifPresent(inventory -> {
            inventory.setAvailableStock(inventory.getAvailableStock() + request.getQuantity());
            inventoryRepository.save(inventory);

            InventoryReply reply = new InventoryReply(request.getOrderId(), ReplyStatus.SUCCESS, "Inventory Released");
            System.out.println("Inventory RELEASED for Order ID: " + request.getOrderId());
            // Send confirmation reply back to the Orchestrator
            rabbitTemplate.convertAndSend(RabbitMQConfig.SAGA_REPLY_QUEUE, reply);
        });
    }
}