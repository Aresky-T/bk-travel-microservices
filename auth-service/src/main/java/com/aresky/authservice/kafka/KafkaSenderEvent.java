package com.aresky.authservice.kafka;

import com.aresky.authservice.util.RandomUtils;
import com.google.gson.Gson;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import reactor.kafka.sender.SenderResult;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Service
public class KafkaSenderEvent {
    public static final String KEY = "auth-service-key";

    @Autowired
    private KafkaSender<String, String> kafkaSender;

    private final Duration TIMEOUT = Duration.ofMillis(2000);
    private final Logger log = LoggerFactory.getLogger(KafkaSenderEvent.class);
    private final Gson gson = new Gson();

    public void sendMessage(String topic, String message){
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, KEY, message);
        SenderRecord<String, String, String> senderRecord = SenderRecord.create(producerRecord, RandomUtils.randomString(16));
        send(senderRecord);
    }

    public void sendMessage(String topic, String key, Object value){
        // Convert value to String
        String message = gson.toJson(value);

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, message);
        SenderRecord<String, String, String> senderRecord = SenderRecord.create(producerRecord, RandomUtils.randomString(16));
        send(senderRecord);
    }

    private void send(SenderRecord<String, String, String> record){
        kafkaSender.send(Mono.just(record))
                .timeout(TIMEOUT)
                .next()
                .onErrorMap(TimeoutException.class, e -> new RuntimeException("Cannot connect to Kafka server within timeout"))
                .map(SenderResult::recordMetadata)
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(value -> {
                    log.info("Message sent to Kafka successfully with topic {}", value.topic());
                }, error -> {
                    log.error("Error sending message to Notification service by Kafka: {}", error.getMessage());
                });
    }
}
