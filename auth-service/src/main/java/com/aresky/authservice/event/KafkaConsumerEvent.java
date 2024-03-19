package com.aresky.authservice.event;

import com.aresky.authservice.constants.KafkaTopic;
import com.aresky.authservice.service.IAuthService;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOffset;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;
import reactor.kafka.sender.KafkaSender;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class KafkaConsumerEvent {

    @Autowired
    private ReceiverOptions<String, String> receiverOptions;

    @Autowired
    private KafkaProducerEvent producerEvent;

    @Autowired
    private IAuthService authService;

    private Disposable disposable;

    private final List<String> topics = List.of(
            KafkaTopic.AUTH_VALIDATION_REQUEST,
            KafkaTopic.AUTH_VALIDATION_RESPONSE
    );

    @PostConstruct
    public void startConsumer() {
        KafkaReceiver<String, String> receiver = KafkaReceiver.create(
                receiverOptions.subscription(topics)
        );
        disposable = receiver.receive().subscribe(this::handleReceiveData);
    }

    public void handleReceiveData (ReceiverRecord<String, String> record){
        String receivedMessage = record.value();
        switch (record.topic()){
            case KafkaTopic.AUTH_VALIDATION_REQUEST -> {
                try {
                    long authId = Long.parseLong(receivedMessage);
                    log.info("TOPIC \"auth-validation-request\" received value: {}", authId);
                    authService.isValidAuth(authId).subscribe(isValid -> {
                        String sendMessage = isValid ? "valid" : "invalid";
                        producerEvent.sendMessage(new KafkaProducerEvent.CustomMessage(KafkaTopic.AUTH_VALIDATION_RESPONSE, sendMessage))
                                .subscribe();
                    });

                } catch (NumberFormatException ex){
                    log.error("NumberFormatException: {}", ex.getMessage());
                }
            }

            case KafkaTopic.AUTH_VALIDATION_RESPONSE -> {
                log.info("TOPIC \"auth-validation-response\" received value: {}", receivedMessage);
            }
        }
    }

    @PreDestroy
    public void stopConsumer(){
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
