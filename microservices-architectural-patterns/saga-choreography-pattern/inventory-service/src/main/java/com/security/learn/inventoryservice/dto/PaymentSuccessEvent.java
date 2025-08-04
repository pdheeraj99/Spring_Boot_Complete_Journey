package com.security.learn.inventoryservice.dto;

import lombok.Data;

@Data
public class PaymentSuccessEvent {
    private String orderId;
    private String paymentId;
    private String productId;
    private int quantity;
}