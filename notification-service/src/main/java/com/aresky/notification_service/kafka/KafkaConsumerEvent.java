package com.aresky.notification_service.kafka;

import com.aresky.notification_service.dto.request.NotificationRequest;
import com.aresky.notification_service.entity.NotificationType;
import com.aresky.notification_service.service.notification.INotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.util.*;

@Slf4j
@Component
public class KafkaConsumerEvent {
    @Autowired
    private ReceiverOptions<String, String> receiverOptions;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private INotificationService notificationService;

    private Disposable disposable;

    @PostConstruct
    public void startConsumer() {
        notificationService.getAllNotificationTypes()
                .map(NotificationType::getName)
                .collectList()
                .map(typeList -> KafkaReceiver.create(receiverOptions.subscription(typeList)))
                .flatMapMany(KafkaReceiver::receive)
                .flatMap(this::buildNotificationRequest)
                .flatMap(notificationService::createNotification)
                .subscribe();
    }

    private Mono<NotificationRequest> buildNotificationRequest(ReceiverRecord<String, String> record){
        return Mono.justOrEmpty(record)
                .flatMap(rec -> {
                    String topic = rec.topic();
                    String message = rec.value();
                    System.out.println("Received message from topic: " + topic);
                    System.out.println("Message: " + message);

                    Set<String> keys = new HashSet<>();
                    keys.add("userId");
                    keys.add("entityId");
                    keys.add("keywords");

                    return Mono.justOrEmpty(buildNotificationRequest(message, keys))
                            .filter(req -> req.getUserId() != null)
                            .doOnNext(req -> req.setTypeName(topic));
                });
    }

    @PreDestroy
    public void stopConsumer(){
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    @SuppressWarnings("unused")
    private Object convert(String message){
        try {
            return objectMapper.readValue(message, Object.class);
        } catch (JsonProcessingException ex) {
            log.error("JsonProcessingException: {}", ex.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unused")
    private <T> T convert(String message, Class<T> classType){
        try {
            return objectMapper.readValue(message, classType);
        } catch (JsonProcessingException ex) {
            log.error("JsonProcessingException: {}", ex.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unused")
    private <T> T convertFromJsonMessageUsingGson(String jsonString, Class<T> destinationType, Set<String> keys){
        try {
            Gson gson = new Gson();
            JsonObject rootObject = JsonParser.parseString(jsonString).getAsJsonObject();
            JsonObject filteredObject = new JsonObject();

            if (keys != null) keys.forEach(key -> {
                filteredObject.add(key, rootObject.get(key));
            });

            String filteredObjectStr = gson.toJson(filteredObject);
            return gson.fromJson(filteredObjectStr, destinationType);
        } catch (JsonParseException ex){
            log.error("Convert json string failed from value: {}", jsonString);
            return null;
        }
    }

    private NotificationRequest buildNotificationRequest(String jsonString, Set<String> keys){
        try {
            Gson gson = new Gson();
            JsonObject rootObject = JsonParser.parseString(jsonString).getAsJsonObject();
            JsonObject filteredObject = new JsonObject();

            if (keys != null) keys.forEach(key -> {
                filteredObject.add(key, rootObject.get(key));
            });

            String filteredObjectStr = gson.toJson(filteredObject);
            return gson.fromJson(filteredObjectStr, NotificationRequest.class);
        } catch (JsonParseException ex){
            log.error("Build notification request failed from json string: {}", jsonString);
            return null;
        }
    }
}
