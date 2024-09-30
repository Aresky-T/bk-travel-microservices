package com.aresky.bookingservice.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ReactorKafkaConfig {
    private final Class<StringSerializer> keySerializerClass = StringSerializer.class;
    private final Class<StringSerializer> valueSerializerClass = StringSerializer.class;
    private final Class<StringDeserializer> keyDeserializerClass = StringDeserializer.class;
    private final Class<StringDeserializer> valueDeserializerClass = StringDeserializer.class;

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.consumer.group-id}")
    private String groupId;

    @Value("${kafka.consumer.client-id}")
    private String consumerClientId;

    @Value("${kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Bean
    KafkaSender<String, String> kafkaSender(){
        SenderOptions<String, String> senderOptions = getSenderOptions();
        return KafkaSender.create(senderOptions);
    }

    @Bean
    SenderOptions<String, String> getSenderOptions(){
        Map<String, Object> producerProperties = new HashMap<>();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializerClass);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializerClass);

        return SenderOptions.<String, String>create(producerProperties).maxInFlight(1024);
    }

    @Bean
    ReceiverOptions<String, String> getReceiverOptions(){
        Map<String, Object> consumerProperties = new HashMap<>();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        consumerProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, consumerClientId);
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializerClass);
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializerClass);
        consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

        return ReceiverOptions.create(consumerProperties);
    }
}
