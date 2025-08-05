package com.security.learn.common.dto;

import com.security.learn.paymentserviceactor.enums.ReplyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReply {
    private String orderId;
    private String paymentId; // This field helps us identify the reply
    private ReplyStatus status;
    private String reason;
}