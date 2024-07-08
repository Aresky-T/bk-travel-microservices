package com.aresky.notification_service.kafka;

import com.aresky.notification_service.dto.request.NotificationRequest;
import com.aresky.notification_service.service.notification.INotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

    private final Set<String> topics = Set.of(
            KafkaTopic.BOOKING_SUCCESS,
            KafkaTopic.BOOKING_CANCEL_APPROVED,
            KafkaTopic.BOOKING_CANCEL_PENDING,
            KafkaTopic.TOUR_CANCELLED,
            KafkaTopic.TOUR_DAILY_REMINDER,
            KafkaTopic.CUSTOMER_MAIL_RECEIVED,
            KafkaTopic.MAIL_REPLIED,
            KafkaTopic.MAIL_SENT,
            KafkaTopic.VNPAY_PAYMENT_SUCCESS,
            KafkaTopic.FIRST_TIME_LOGIN
    );

    @PostConstruct
    public void startConsumer() {
        KafkaReceiver<String, String> receiver = KafkaReceiver.create(
                receiverOptions.subscription(topics)
        );

        disposable = receiver.receive()
                .flatMap(this::buildNotificationRequest)
                .flatMap(notificationService::createNotification)
                .subscribe();
    }

    private Mono<NotificationRequest> buildNotificationRequest(ReceiverRecord<String, String> record){
        return Mono.justOrEmpty(record)
                .flatMap(rec -> {
                    String topic = rec.topic();
                    String message = rec.value();
                    Set<String> keys = new HashSet<>();
                    keys.add("userId");
                    keys.add("entityId");
                    keys.add("keywords");

                    NotificationRequest request = buildNotificationRequest(message, keys);

                    if(request != null){
                        request.setTypeName(topic);
                    }

                    System.out.println(request);

                    return Mono.justOrEmpty(request);
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
        Gson gson = new Gson();
        JsonObject rootObject = JsonParser.parseString(jsonString).getAsJsonObject();
        JsonObject filteredObject = new JsonObject();

        if (keys != null) keys.forEach(key -> {
            filteredObject.add(key, rootObject.get(key));
        });

        String filteredObjectStr = gson.toJson(filteredObject);
        return gson.fromJson(filteredObjectStr, destinationType);
    }

    private NotificationRequest buildNotificationRequest(String jsonString, Set<String> keys){
        Gson gson = new Gson();
        JsonObject rootObject = JsonParser.parseString(jsonString).getAsJsonObject();
        JsonObject filteredObject = new JsonObject();

        if (keys != null) keys.forEach(key -> {
            filteredObject.add(key, rootObject.get(key));
        });

        String filteredObjectStr = gson.toJson(filteredObject);
        return gson.fromJson(filteredObjectStr, NotificationRequest.class);
    }
}
