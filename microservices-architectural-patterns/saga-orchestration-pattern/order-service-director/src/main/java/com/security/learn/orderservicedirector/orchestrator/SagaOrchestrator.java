package com.security.learn.orderservicedirector.orchestrator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.learn.common.dto.InventoryReply;
import com.security.learn.common.dto.InventoryRequest;
import com.security.learn.common.dto.PaymentReply;
import com.security.learn.common.dto.PaymentRequest;
import com.security.learn.orderservicedirector.config.RabbitMQConfig;
import com.security.learn.orderservicedirector.entity.Order;
import com.security.learn.orderservicedirector.enums.InventoryAction;
import com.security.learn.orderservicedirector.enums.OrderStatus;
import com.security.learn.orderservicedirector.enums.PaymentAction;
import com.security.learn.orderservicedirector.enums.ReplyStatus;
import com.security.learn.orderservicedirector.repo.OrderRepository;
import com.security.learn.orderservicedirector.service.OrderService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SagaOrchestrator {

    @Autowired private RabbitTemplate rabbitTemplate;
    @Autowired private OrderService orderService;
    @Autowired private OrderRepository orderRepository;
    @Autowired private ObjectMapper objectMapper;

    public void startSaga(Order order) {
        System.out.println("SAGA STARTED for Order ID: " + order.getOrderId());
        PaymentRequest paymentRequest = new PaymentRequest(order.getOrderId(), order.getAmount(), PaymentAction.PROCESS);
        rabbitTemplate.convertAndSend(RabbitMQConfig.PAYMENT_REQUEST_QUEUE, paymentRequest);
    }

    @RabbitListener(queues = RabbitMQConfig.SAGA_REPLY_QUEUE)
    public void onSagaReply(Message message) {
        try {
            String jsonBody = new String(message.getBody());

            // A more robust way to check the message type
            if (jsonBody.contains("\"paymentId\"")) {
                handlePaymentReply(objectMapper.readValue(jsonBody, PaymentReply.class));
            } else {
                // If it's not a payment reply, it must be an inventory reply
                handleInventoryReply(objectMapper.readValue(jsonBody, InventoryReply.class));
            }
        } catch (Exception e) {
            System.err.println("Error processing saga reply: " + e.getMessage());
        }
    }

    private void handlePaymentReply(PaymentReply reply) {
        System.out.println("Orchestrator received payment reply for Order ID: " + reply.getOrderId() + " with status: " + reply.getStatus());
        orderRepository.findById(reply.getOrderId()).ifPresent(order -> {
            if (reply.getStatus() == ReplyStatus.SUCCESS) {
                if (reply.getReason() != null && reply.getReason().contains("Refunded")) {
                    // This is a refund confirmation - saga compensation is complete
                    System.out.println("SAGA COMPENSATION COMPLETED: Payment refunded for Order ID: " + order.getOrderId());
                    // Order should already be cancelled, but ensure it's in the right state
                    if (order.getStatus() != OrderStatus.CANCELLED) {
                        orderService.cancelOrder(order.getOrderId());
                    }
                } else {
                    // This is initial payment success, move to next step: Inventory
                    InventoryRequest inventoryRequest = new InventoryRequest(order.getOrderId(), order.getProductId(), order.getQuantity(), InventoryAction.RESERVE);
                    rabbitTemplate.convertAndSend(RabbitMQConfig.INVENTORY_REQUEST_QUEUE, inventoryRequest);
                }
            } else {
                // Payment failed, cancel order
                orderService.cancelOrder(order.getOrderId());
            }
        });
    }

    private void handleInventoryReply(InventoryReply reply) {
        System.out.println("Orchestrator received inventory reply for Order ID: " + reply.getOrderId() + " with status: " + reply.getStatus());
        orderRepository.findById(reply.getOrderId()).ifPresent(order -> {
            if (reply.getStatus() == ReplyStatus.SUCCESS) {
                // Inventory success, saga is complete
                orderService.confirmOrder(order.getOrderId());
            } else {
                System.out.println("Inventory failed, compensating payment for Order ID: " + order.getOrderId());
                PaymentRequest paymentRequest = new PaymentRequest(order.getOrderId(), order.getAmount(), PaymentAction.REFUND);
                rabbitTemplate.convertAndSend(RabbitMQConfig.PAYMENT_REQUEST_QUEUE, paymentRequest);
            }
        });
    }
}