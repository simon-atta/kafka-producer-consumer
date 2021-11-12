package com.kafka.controller;

import com.kafka.model.EventRequest;
import com.kafka.service.producer.ProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public final class KafkaController {
    private final ProducerService producerService;

    public KafkaController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestBody EventRequest message) {
        producerService.sendMessage(message);
    }
}