package com.example.demorabbitmq.consumer;

import com.example.demorabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.json.queue.name}"})
    public void consume(User user) {
        LOGGER.info(String.format("Received message JSON -> %s", user.toString()));
    }
}
