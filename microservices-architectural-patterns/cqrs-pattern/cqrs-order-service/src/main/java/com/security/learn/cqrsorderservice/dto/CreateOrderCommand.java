package com.security.learn.cqrsorderservice.dto;

import lombok.Data;

@Data
public class CreateOrderCommand {
    private String productId;
    private int quantity;
    private double price;
    private String shippingAddress;
}