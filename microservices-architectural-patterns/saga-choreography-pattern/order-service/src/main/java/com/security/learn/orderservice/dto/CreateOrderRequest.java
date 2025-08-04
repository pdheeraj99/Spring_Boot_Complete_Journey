package com.security.learn.orderservice.dto;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private String productId;
    private int quantity;
    private double amount;
}