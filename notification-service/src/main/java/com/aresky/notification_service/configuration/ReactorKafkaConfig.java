package com.aresky.notification_service.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ReactorKafkaConfig {
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    // Producer properties
    private final Class<StringSerializer> keySerializerClass = StringSerializer.class;
    private final Class<StringSerializer> valueSerializerClass = StringSerializer.class;

    // Consumer properties
    @Value("${kafka.consumer.group-id}")
    private String groupID;

    @Value("${kafka.consumer.client-id}")
    private String clientID;

    @Value("${kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    private final Class<StringDeserializer> keyDeserializerClass = StringDeserializer.class;
    private final Class<StringDeserializer> valueDeserializerClass = StringDeserializer.class;

    @Bean
    KafkaSender<String, String> sender() {
        return KafkaSender.create(getSenderOptions());
    }

    @Bean
    SenderOptions<String, String> getSenderOptions() {
        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializerClass);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializerClass);

        return SenderOptions.<String, String>create(producerProps)
                .maxInFlight(1024);
    }

    @Bean
    ReceiverOptions<String, String> getReceiverOptions() {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupID);
        consumerProps.put(ConsumerConfig.CLIENT_ID_CONFIG, clientID);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializerClass);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializerClass);

        return ReceiverOptions.<String, String>create(consumerProps);
    }
}
