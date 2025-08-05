package com.security.learn.inventoryserviceactor.listener;

import com.security.learn.common.dto.InventoryRequest;
import com.security.learn.inventoryserviceactor.config.RabbitMQConfig;
import com.security.learn.inventoryserviceactor.service.InventoryService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventorySagaListener {
    @Autowired
    private InventoryService inventoryService;

    @RabbitListener(queues = RabbitMQConfig.INVENTORY_REQUEST_QUEUE)
    public void onInventoryRequest(InventoryRequest request) {
        System.out.println("Received command: " + request.getAction() + " for Order ID: " + request.getOrderId());

        switch (request.getAction()) {
            case RESERVE:
                inventoryService.reserveInventory(request);
                break;
            case RELEASE:
                inventoryService.releaseInventory(request);
                break;
        }
    }
}