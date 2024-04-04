package com.aresky.authservice.event;

//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

//import lombok.Data;
//import lombok.NoArgsConstructor;
//import reactor.core.publisher.Mono;
//import reactor.kafka.sender.KafkaSender;
//import reactor.kafka.sender.SenderRecord;

//@Service
public class KafkaProducerEvent {
//
//    @Autowired
//    private KafkaSender<String, String> sender;
//
//    public Mono<String> sendMessage(CustomMessage message) {
//        ProducerRecord<String, String> record = new ProducerRecord<>(message.topic, message.value);
//
//        return sender
//                .send(Mono.just(SenderRecord.create(record, message.value)))
//                .then()
//                .thenReturn("sent");
//    }
//
//    @Data
//    @NoArgsConstructor
//    public static class CustomMessage {
//        private String topic;
//        private String value;
//
//        public CustomMessage(String topic, String value) {
//            this.topic = topic;
//            this.value = value;
//        }
//    }
}
