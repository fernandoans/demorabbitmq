package com.example.demorabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.json.queue.name}")
    private String jsonQueue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key.name}")
    private String routingKey;

    @Value("${rabbitmq.json.routing.key.name}")
    private String jsonRoutingKey;

    // Spring Bean for RabbitMQ Queue (store String messages)
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    // Spring Bean for RabbitMQ Queue (store JSON messages)
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }

    // Spring Bean for RabbitMQ Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    // Binding between Queue (String) and Exchange using Routing Key
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
            .to(exchange())
            .with(routingKey);
    }

    // Binding between Queue (JSON) and Exchange using JSON Routing Key
    @Bean
    public Binding jasonBinding() {
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(jsonRoutingKey);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    // Sprint automatically configure:
    //  - ConnectionFactory
    //  - RabbitAdmin
}
