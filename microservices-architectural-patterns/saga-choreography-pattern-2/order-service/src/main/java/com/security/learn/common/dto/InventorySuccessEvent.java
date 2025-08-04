package com.security.learn.common.dto;

import lombok.Data;

@Data
public class InventorySuccessEvent implements SagaEvent { // Add this part
    private String orderId;
}