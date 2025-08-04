package com.security.learn.orderservice.dto;

import lombok.Data;

@Data
public class InventorySuccessEvent {
    private String orderId;
}