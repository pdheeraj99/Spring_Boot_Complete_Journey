package com.security.learn.common.dto;

import com.security.learn.inventoryserviceactor.enums.ReplyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryReply {
    private String orderId;
    private ReplyStatus status;
    private String reason;
}