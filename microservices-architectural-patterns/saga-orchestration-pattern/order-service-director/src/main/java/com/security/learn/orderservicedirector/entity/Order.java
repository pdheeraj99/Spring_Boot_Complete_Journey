package com.security.learn.orderservicedirector.entity;

import com.security.learn.orderservicedirector.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    private String orderId;
    private String productId;
    private int quantity;
    private double amount;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}