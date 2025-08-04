package com.security.learn.common.dto;

import lombok.Data;

@Data
public class PaymentSuccessEvent {
    private String orderId;
    private String paymentId;
    private String productId;
    private int quantity;
}