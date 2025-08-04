package com.security.learn.paymentservice.listener;

import com.security.learn.paymentservice.dto.OrderCreatedEvent;
import com.security.learn.paymentservice.dto.PaymentSuccessEvent;
import com.security.learn.paymentservice.entity.Payment;
import com.security.learn.paymentservice.enums.PaymentStatus;
import com.security.learn.paymentservice.rabbitconfig.RabbitMQConfig;
import com.security.learn.paymentservice.repo.PaymentRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentSagaListener {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.PAYMENT_QUEUE)
    public void onOrderCreated(OrderCreatedEvent event) {
        System.out.println("Received ORDER_CREATED_KEY for Order ID: " + event.getOrderId());

        Payment payment = new Payment();
        payment.setOrderId(event.getOrderId());
        payment.setAmount(event.getAmount());
        // For the happy path, we assume payment is always successful
        payment.setStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(payment);
        System.out.println("Payment SUCCESS for Order ID: " + event.getOrderId());

        PaymentSuccessEvent nextEvent = new PaymentSuccessEvent(
                event.getOrderId(),
                payment.getPaymentId(),
                event.getProductId(),
                event.getQuantity()
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.PAYMENT_SUCCESS_KEY, nextEvent);
        System.out.println("Published PAYMENT_SUCCESS_KEY for Order ID: " + event.getOrderId());
    }
}