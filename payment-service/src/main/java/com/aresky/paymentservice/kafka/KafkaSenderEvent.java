package com.aresky.paymentservice.kafka;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaSenderEvent {

    @Autowired
    private KafkaTemplate<String, Object> template;

    public final String KEY = "payment-service-key";
    private final Gson gson = new Gson();

    public void sendMessage(String topic, Object value){
        String message = gson.toJson(value);
        CompletableFuture<SendResult<String, Object>> future = template.send(topic, KEY, message);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("Error sending message: " + ex.getMessage());
            } else {
                System.out.println("Sent message to topic " + topic);
                System.out.println("Message sent successfully");
            }
        });
    }
}
