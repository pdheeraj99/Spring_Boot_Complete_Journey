package com.security.learn.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSuccessEvent {
    private String orderId;
    private String paymentId;
    private String productId;
    private int quantity;
}