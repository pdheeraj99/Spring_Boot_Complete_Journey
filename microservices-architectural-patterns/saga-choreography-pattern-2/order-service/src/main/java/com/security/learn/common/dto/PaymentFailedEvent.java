package com.security.learn.common.dto;

import lombok.Data;

@Data
public class PaymentFailedEvent implements SagaEvent { // Add this part
    private String orderId;
    private String reason;
}