package com.security.learn.paymentserviceactor.entity;

import com.security.learn.paymentserviceactor.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "PAYMENTS")
public class Payment {
    @Id
    private String paymentId;
    private String orderId;
    private double amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public Payment() {
        this.paymentId = UUID.randomUUID().toString();
    }
}