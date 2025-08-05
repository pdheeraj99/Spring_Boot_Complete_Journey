package com.security.learn.paymentserviceactor.service;

import com.security.learn.common.dto.PaymentReply;
import com.security.learn.common.dto.PaymentRequest;
import com.security.learn.paymentserviceactor.config.RabbitMQConfig;
import com.security.learn.paymentserviceactor.entity.Payment;
import com.security.learn.paymentserviceactor.enums.PaymentStatus;
import com.security.learn.paymentserviceactor.enums.ReplyStatus;
import com.security.learn.paymentserviceactor.repo.PaymentRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired private RabbitTemplate rabbitTemplate;

    public void processPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        PaymentReply reply;

        if (request.getAmount() > 1000) { // Failure simulation
            payment.setStatus(PaymentStatus.FAILURE);
            reply = new PaymentReply(request.getOrderId(), payment.getPaymentId(), ReplyStatus.FAILURE, "Amount exceeds limit");
            System.out.println("Payment FAILED for Order ID: " + request.getOrderId());
        } else {
            payment.setStatus(PaymentStatus.SUCCESS);
            reply = new PaymentReply(request.getOrderId(), payment.getPaymentId(), ReplyStatus.SUCCESS, null);
            System.out.println("Payment SUCCESS for Order ID: " + request.getOrderId());
        }
        paymentRepository.save(payment);
        // Send reply back to the Orchestrator
        rabbitTemplate.convertAndSend(RabbitMQConfig.SAGA_REPLY_QUEUE, reply);
    }

    public void refundPayment(PaymentRequest request) {
        paymentRepository.findByOrderId(request.getOrderId()).ifPresent(payment -> {
            payment.setStatus(PaymentStatus.REFUNDED);
            paymentRepository.save(payment);
            PaymentReply reply = new PaymentReply(request.getOrderId(), payment.getPaymentId(), ReplyStatus.SUCCESS, "Payment Refunded");
            System.out.println("Payment REFUNDED for Order ID: " + request.getOrderId());
            // Send confirmation reply back to the Orchestrator
            rabbitTemplate.convertAndSend(RabbitMQConfig.SAGA_REPLY_QUEUE, reply);
        });
    }
}