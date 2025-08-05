package com.security.learn.common.dto;

import com.security.learn.orderservicedirector.enums.ReplyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryReply {
    private String orderId;
    // No unique fields needed here, the absence of 'paymentId' is enough
    private ReplyStatus status;
    private String reason;
}