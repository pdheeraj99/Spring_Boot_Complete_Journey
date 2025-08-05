package com.security.learn.paymentserviceactor.listener;

import com.security.learn.common.dto.PaymentRequest;
import com.security.learn.paymentserviceactor.config.RabbitMQConfig;
import com.security.learn.paymentserviceactor.service.PaymentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentSagaListener {
    @Autowired
    private PaymentService paymentService;

    @RabbitListener(queues = RabbitMQConfig.PAYMENT_REQUEST_QUEUE)
    public void onPaymentRequest(PaymentRequest request) {
        System.out.println("Received command: " + request.getAction() + " for Order ID: " + request.getOrderId());

        switch (request.getAction()) {
            case PROCESS:
                paymentService.processPayment(request);
                break;
            case REFUND:
                paymentService.refundPayment(request);
                break;
        }
    }
}