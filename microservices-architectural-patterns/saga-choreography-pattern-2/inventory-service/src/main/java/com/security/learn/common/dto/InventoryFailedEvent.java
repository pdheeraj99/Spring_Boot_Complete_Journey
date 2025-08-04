package com.security.learn.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryFailedEvent {
    private String orderId;
    private String productId;
    private int quantity;
    private String reason;
}