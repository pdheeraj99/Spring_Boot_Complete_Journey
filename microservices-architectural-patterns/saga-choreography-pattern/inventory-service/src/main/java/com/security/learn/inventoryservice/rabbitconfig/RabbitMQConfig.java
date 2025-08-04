package com.security.learn.inventoryservice.rabbitconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // The central Post Office
    public static final String EXCHANGE = "saga_exchange";

    // Mailboxes for each service
    public static final String ORDER_QUEUE = "order_queue";
    public static final String PAYMENT_QUEUE = "payment_queue";
    public static final String INVENTORY_QUEUE = "inventory_queue";

    // Address labels (Routing Keys) for our letters (Events)
    public static final String ORDER_CREATED_KEY = "order.created";
    public static final String PAYMENT_SUCCESS_KEY = "payment.success";
    public static final String PAYMENT_FAILURE_KEY = "payment.failure";
    public static final String INVENTORY_SUCCESS_KEY = "inventory.success";
    public static final String INVENTORY_FAILURE_KEY = "inventory.failure";

    // ===== BEANS TO CREATE THE INFRASTRUCTURE =====

    @Bean public TopicExchange exchange() { return new TopicExchange(EXCHANGE); }
    @Bean public Queue orderQueue() { return new Queue(ORDER_QUEUE); }
    @Bean public Queue paymentQueue() { return new Queue(PAYMENT_QUEUE); }
    @Bean public Queue inventoryQueue() { return new Queue(INVENTORY_QUEUE); }

    // ===== DELIVERY RULES (BINDINGS) =====

    // Rule 1: Send "order created" letters to the payment mailbox
    @Bean public Binding orderToPaymentBinding() { return BindingBuilder.bind(paymentQueue()).to(exchange()).with(ORDER_CREATED_KEY); }

    // Rule 2: Send "payment success" letters to the inventory mailbox
    @Bean public Binding paymentToInventoryBinding() { return BindingBuilder.bind(inventoryQueue()).to(exchange()).with(PAYMENT_SUCCESS_KEY); }

    // Rule 3: Send "inventory success" letters to the order mailbox (to complete the saga)
    @Bean public Binding inventoryToOrderBinding() { return BindingBuilder.bind(orderQueue()).to(exchange()).with(INVENTORY_SUCCESS_KEY); }

    // Rule 4 (Failure): Send "payment failure" letters back to the order mailbox
    @Bean public Binding paymentFailureToOrderBinding() { return BindingBuilder.bind(orderQueue()).to(exchange()).with(PAYMENT_FAILURE_KEY); }

    // Rule 5 (Failure): Send "inventory failure" letters back to the order mailbox
    @Bean
    public Binding inventoryFailureToOrderBinding() { return BindingBuilder.bind(orderQueue()).to(exchange()).with(INVENTORY_FAILURE_KEY); }

    // Rule 6 (Failure): Send "inventory failure" letters back to the payment mailbox (for rollback)
    @Bean public Binding inventoryFailureToPaymentBinding() { return BindingBuilder.bind(paymentQueue()).to(exchange()).with(INVENTORY_FAILURE_KEY); }


    // ===== BEANS FOR JSON MESSAGE CONVERSION =====

    @Bean public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean public RabbitTemplate rabbitTemplate(ConnectionFactory factory, Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(converter);
        return template;
    }
}