package com.security.learn.common.dto;

import lombok.Data;

@Data
public class OrderCreatedEvent {
    private String orderId;
    private String productId;
    private int quantity;
    private double amount;
}