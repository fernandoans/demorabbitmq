package com.example.demorabbitmq.controller;

import com.example.demorabbitmq.dto.User;
import com.example.demorabbitmq.publisher.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {

    private final RabbitMQJsonProducer jasonProducer;

    public MessageJsonController(RabbitMQJsonProducer jasonProducer) {
        this.jasonProducer = jasonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody User user) {
        jasonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("JSON Message send to RabbitMQ!");
    }
}
