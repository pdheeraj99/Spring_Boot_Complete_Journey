package com.security.learn.common.dto;

import com.security.learn.paymentserviceactor.enums.PaymentAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private String orderId;
    private double amount;
    private PaymentAction action;
}