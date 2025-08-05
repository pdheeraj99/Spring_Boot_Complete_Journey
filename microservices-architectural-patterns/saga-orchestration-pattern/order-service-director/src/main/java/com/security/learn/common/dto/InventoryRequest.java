package com.security.learn.common.dto;

import com.security.learn.orderservicedirector.enums.InventoryAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequest {
    private String orderId;
    private String productId;
    private int quantity;
    private InventoryAction action;
}