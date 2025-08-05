package com.security.learn.cqrsorderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSummaryDTO {
    private String orderId;
    private String productId;
    private String status;
}