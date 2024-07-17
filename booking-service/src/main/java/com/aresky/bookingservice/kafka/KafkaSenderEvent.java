package com.aresky.bookingservice.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import reactor.kafka.sender.SenderResult;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Service
public class KafkaSenderEvent {

    @Autowired
    private KafkaSender<String, String> kafkaSender;

    private final Duration TIMEOUT = Duration.ofMillis(2000);

    public Mono<RecordMetadata> sendMessage(String topic, String key, String value) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);
        SenderRecord<String, String, String> record = SenderRecord.create(producerRecord, key);
        return kafkaSender.send(Mono.just(record))
                .timeout(TIMEOUT)
                .next()
                .onErrorMap(TimeoutException.class, e -> new RuntimeException("Cannot connect to Kafka server within timeout"))
                .map(SenderResult::recordMetadata);
    }

    public Mono<RecordMetadata> sendMessage(String topic, String message) {
        String key = "booking-service-key";
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, message);
        SenderRecord<String, String, String> record = SenderRecord.create(producerRecord, key);
        return kafkaSender.send(Mono.just(record))
                .timeout(TIMEOUT)
                .next()
                .onErrorMap(TimeoutException.class, e -> new RuntimeException("Cannot connect to Kafka server within timeout"))
                .map(SenderResult::recordMetadata);
    }
}
