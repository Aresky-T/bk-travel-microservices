package com.aresky.bookingservice.kafka;

import com.aresky.bookingservice.model.EBookingStatus;
import com.aresky.bookingservice.model.EFormOfPayment;
import com.aresky.bookingservice.service.booking.IBookingService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
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

    @Autowired
    private ReceiverOptions<String, String> receiverOptions;

    @Autowired
    private IBookingService bookingService;

    private Disposable disposable;

    private final Set<String> TOPICS = Set.of(
            KafkaTopic.BOOKING_WITH_VNPAY_SUCCESS
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
                    if (topic.equals(KafkaTopic.BOOKING_WITH_VNPAY_SUCCESS)) {
                        String message = record.value();
                        Set<String> requiredKeys = new HashSet<>();
                        requiredKeys.add("userId");
                        requiredKeys.add("entityId");
                        requiredKeys.add("keywords");

                        KafkaMessageType.NotificationRequest notification = convertToNotificationRequest(message, requiredKeys);

                        Integer bookingId = notification.getEntityId();
                        bookingService.findBookingBy(bookingId)
                                .flatMap(booking -> {
                                    booking.setStatus(EBookingStatus.PAY_UP);
                                    booking.setFormOfPayment(EFormOfPayment.VNPAY_ON_WEBSITE);
                                    return bookingService.saveBooking(booking).then();
                                });
                    }

                    return Mono.empty();
                });
    }

    @PreDestroy
    public void stopConsumer(){
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    private KafkaMessageType.NotificationRequest convertToNotificationRequest(String jsonString, Set<String> keys){
        return convertFromJsonMessageUsingGson(jsonString, KafkaMessageType.NotificationRequest.class, keys);
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
}
