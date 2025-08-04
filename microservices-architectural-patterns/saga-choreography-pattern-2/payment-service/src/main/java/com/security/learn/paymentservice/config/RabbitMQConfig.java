package com.security.learn.paymentservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // ... All your constants for EXCHANGE, QUEUES, and ROUTING_KEYS ...
    public static final String EXCHANGE = "saga_exchange";
    public static final String ORDER_QUEUE = "order_queue";
    public static final String PAYMENT_QUEUE = "payment_queue";
    public static final String INVENTORY_QUEUE = "inventory_queue";
    public static final String ORDER_CREATED_KEY = "order.created";
    public static final String PAYMENT_SUCCESS_KEY = "payment.success";
    public static final String PAYMENT_FAILURE_KEY = "payment.failure";
    public static final String INVENTORY_SUCCESS_KEY = "inventory.success";
    public static final String INVENTORY_FAILURE_KEY = "inventory.failure";

    // ... All your beans for Exchange, Queues, and Bindings ...
    @Bean public TopicExchange exchange() { return new TopicExchange(EXCHANGE); }
    @Bean public Queue orderQueue() { return new Queue(ORDER_QUEUE); }
    @Bean public Queue paymentQueue() { return new Queue(PAYMENT_QUEUE); }
    @Bean public Queue inventoryQueue() { return new Queue(INVENTORY_QUEUE); }
    @Bean public Binding orderToPaymentBinding() { return BindingBuilder.bind(paymentQueue()).to(exchange()).with(ORDER_CREATED_KEY); }
    @Bean public Binding paymentToInventoryBinding() { return BindingBuilder.bind(inventoryQueue()).to(exchange()).with(PAYMENT_SUCCESS_KEY); }
    @Bean public Binding inventoryToOrderBinding() { return BindingBuilder.bind(orderQueue()).to(exchange()).with(INVENTORY_SUCCESS_KEY); }
    @Bean public Binding paymentFailureToOrderBinding() { return BindingBuilder.bind(orderQueue()).to(exchange()).with(PAYMENT_FAILURE_KEY); }
    @Bean public Binding inventoryFailureToOrderBinding() { return BindingBuilder.bind(orderQueue()).to(exchange()).with(INVENTORY_FAILURE_KEY); }
    @Bean public Binding inventoryFailureToPaymentBinding() { return BindingBuilder.bind(paymentQueue()).to(exchange()).with(INVENTORY_FAILURE_KEY); }


    // ===== BEANS FOR JSON MESSAGE CONVERSION (THE FINAL FIX) =====

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter(new ObjectMapper().findAndRegisterModules());
    }

    // THIS NEW BEAN IS THE CRITICAL FIX
    // It forces all @RabbitListener annotations to use our flexible JSON converter.
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory, MessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(converter);
        return template;
    }
}