package com.security.learn.common.dto;

import lombok.Data;

@Data
public class InventoryFailedEvent {
    private String orderId;
    private String productId;
    private int quantity;
    private String reason;
}