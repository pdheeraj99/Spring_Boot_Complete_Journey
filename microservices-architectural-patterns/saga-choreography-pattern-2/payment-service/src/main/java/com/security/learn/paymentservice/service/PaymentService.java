package com.security.learn.paymentservice.service;

import com.security.learn.common.dto.InventoryFailedEvent;
import com.security.learn.paymentservice.config.RabbitMQConfig;
import com.security.learn.common.dto.OrderCreatedEvent;
import com.security.learn.common.dto.PaymentFailedEvent;
import com.security.learn.common.dto.PaymentSuccessEvent;
import com.security.learn.paymentservice.entity.Payment;
import com.security.learn.paymentservice.enums.PaymentStatus;
import com.security.learn.paymentservice.repo.PaymentRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void processPayment(OrderCreatedEvent event) {
        Payment payment = new Payment();
        payment.setOrderId(event.getOrderId());
        payment.setAmount(event.getAmount());

        // THE FAILURE LOGIC
        if (event.getAmount() > 1000) {
            payment.setStatus(PaymentStatus.FAILURE);
            paymentRepository.save(payment);

            PaymentFailedEvent failureEvent = new PaymentFailedEvent(event.getOrderId(), "Amount exceeds limit of 1000");
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.PAYMENT_FAILURE_KEY, failureEvent);
            System.out.println("Payment FAILED. Published PAYMENT_FAILURE_KEY for Order ID: " + event.getOrderId());
        } else {
            // THE HAPPY PATH LOGIC
            payment.setStatus(PaymentStatus.SUCCESS);
            paymentRepository.save(payment);

            PaymentSuccessEvent successEvent = new PaymentSuccessEvent(
                    event.getOrderId(),
                    payment.getPaymentId(),
                    event.getProductId(),
                    event.getQuantity()
            );
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.PAYMENT_SUCCESS_KEY, successEvent);
            System.out.println("Payment SUCCESS. Published PAYMENT_SUCCESS_KEY for Order ID: " + event.getOrderId());
        }
    }

    // ADD THIS NEW METHOD
    public void refundPayment(InventoryFailedEvent event) {
        paymentRepository.findByOrderId(event.getOrderId()).ifPresent(payment -> {
            payment.setStatus(PaymentStatus.REFUNDED);
            paymentRepository.save(payment);
            System.out.println("Payment REFUNDED for Order ID: " + event.getOrderId());
        });
    }
}