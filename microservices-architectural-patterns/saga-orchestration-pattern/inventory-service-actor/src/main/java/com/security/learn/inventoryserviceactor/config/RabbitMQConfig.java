package com.security.learn.inventoryserviceactor.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Command queue for the Payment service
    public static final String PAYMENT_REQUEST_QUEUE = "payment.request.queue";
    // Command queue for the Inventory service
    public static final String INVENTORY_REQUEST_QUEUE = "inventory.request.queue";
    // Single reply queue for the Orchestrator to listen to
    public static final String SAGA_REPLY_QUEUE = "saga.reply.queue";

    // Infrastructure Beans
    @Bean
    public Queue paymentRequestQueue() { return new Queue(PAYMENT_REQUEST_QUEUE); }
    @Bean public Queue inventoryRequestQueue() { return new Queue(INVENTORY_REQUEST_QUEUE); }
    @Bean public Queue sagaReplyQueue() { return new Queue(SAGA_REPLY_QUEUE); }

    // JSON Message Converter Beans
    @Bean public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter(new ObjectMapper().findAndRegisterModules());
    }

    @Bean public RabbitTemplate rabbitTemplate(ConnectionFactory factory, MessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(converter);
        return template;
    }
}