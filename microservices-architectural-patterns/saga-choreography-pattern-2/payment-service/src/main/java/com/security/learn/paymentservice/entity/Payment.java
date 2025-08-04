package com.security.learn.paymentservice.entity;

import com.security.learn.paymentservice.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

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
    @Column(length = 20)
    private PaymentStatus status;

    public Payment() {
        this.paymentId = UUID.randomUUID().toString();
    }
}