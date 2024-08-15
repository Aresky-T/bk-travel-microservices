package com.aresky.bookingservice.kafka;

import com.aresky.bookingservice.model.EBookingStatus;
import com.aresky.bookingservice.model.EFormOfPayment;
import com.aresky.bookingservice.service.booking.IBookingService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.util.HashSet;
import java.util.Set;

@Service
public class KafkaReceiverEvent {

    private static final Logger log = LoggerFactory.getLogger(KafkaReceiverEvent.class);

    @Autowired
    private ReceiverOptions<String, String> receiverOptions;

    @Autowired
    private IBookingService bookingService;

    private Disposable disposable;

    private final Set<String> TOPICS = Set.of(
            KafkaTopic.BOOKING_WITH_VNPAY_SUCCESS,
            KafkaTopic.BOOKING_WITH_VNPAY_FAILED,
            KafkaTopic.BOOKING_WITH_VNPAY_CANCELLED,
            KafkaTopic.VNPAY_PAYMENT_SUCCESS
    );

    @PostConstruct
    public void startConsumer(){
        KafkaReceiver<String, String> receiver = KafkaReceiver.create(receiverOptions.subscription(TOPICS));
        this.disposable = receiver.receive()
                .flatMap(this::handleReceivedMessage)
                .subscribe();
    }

    private Mono<Void> handleReceivedMessage(ReceiverRecord<String, String> record){
        return Mono.just(record.topic())
                .flatMap(topic -> {
                    System.out.println("Received message from topic " + topic);
                    String message = record.value();
                    KafkaMessageType.NotificationRequest notification = convertToNotificationRequest(message);

                    if(notification == null){
                        return Mono.empty();
                    }

                    System.out.println(notification);
                    Integer bookingId = notification.getEntityId();

                    return switch (topic) {
                        case KafkaTopic.BOOKING_WITH_VNPAY_SUCCESS, KafkaTopic.VNPAY_PAYMENT_SUCCESS -> bookingService.findBookingBy(bookingId)
                                .filter(booking -> !booking.getStatus().equals(EBookingStatus.PAY_UP))
                                .switchIfEmpty(Mono.empty())
                                .flatMap(booking -> {
                                    booking.setStatus(EBookingStatus.PAY_UP);
                                    booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                                    return bookingService.saveBooking(booking).then();
                                });
                        case KafkaTopic.BOOKING_WITH_VNPAY_FAILED, KafkaTopic.BOOKING_WITH_VNPAY_CANCELLED -> bookingService.delete(bookingId).then();
                        default -> Mono.empty();
                    };
                });
    }

    @PreDestroy
    @SuppressWarnings("unused")
    public void stopConsumer(){
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    private KafkaMessageType.NotificationRequest convertToNotificationRequest(String jsonString){
        Set<String> requiredKeys = new HashSet<>();
        requiredKeys.add("userId");
        requiredKeys.add("entityId");
        requiredKeys.add("keywords");
        return convertFromJsonMessageUsingGson(jsonString, KafkaMessageType.NotificationRequest.class, requiredKeys);
    }

    @SuppressWarnings("unused")
    private KafkaMessageType.NotificationRequest convertToNotificationRequest(String jsonString, Set<String> requiredKeys){
        return convertFromJsonMessageUsingGson(jsonString, KafkaMessageType.NotificationRequest.class, requiredKeys);
    }

    @SuppressWarnings("unused")
    private <T> T convertFromJsonMessageUsingGson(String jsonString, Class<T> destinationType, Set<String> requiredKeys){
        try {

            Gson gson = new Gson();
            JsonObject rootObject = JsonParser.parseString(jsonString).getAsJsonObject();
            JsonObject filteredObject = new JsonObject();

            if (requiredKeys != null) requiredKeys.forEach(key -> {
                filteredObject.add(key, rootObject.get(key));
            });

            String filteredObjectStr = gson.toJson(filteredObject);
            return gson.fromJson(filteredObjectStr, destinationType);
        } catch (JsonSyntaxException | IllegalStateException | NullPointerException ex){
            log.error("Error: {}", ex.getMessage());
            return null;
        }
    }
}
