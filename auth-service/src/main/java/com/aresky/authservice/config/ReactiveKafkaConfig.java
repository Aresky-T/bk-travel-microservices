package com.aresky.authservice.config;

//@Configuration
public class ReactiveKafkaConfig {

//    @Value("${kafka.bootstrap-servers.url}")
//    private String bootstrapServers;
//
//    // Producer properties
//    private final Class<StringSerializer> keySerializerClass = StringSerializer.class;
//    private final Class<StringSerializer> valueSerializerClass = StringSerializer.class;
//
//    // Consumer properties
//    @Value("${kafka.consumer.group-id}")
//    private String groupID;
//
//    @Value("${kafka.consumer.auto-offset-reset}")
//    private String autoOffsetReset;
//
//    private final Class<StringDeserializer> keyDeserializerClass = StringDeserializer.class;
//    private final Class<StringDeserializer> valueDeserializerClass = StringDeserializer.class;
//
//    @Bean
//    KafkaSender<String, String> sender() {
//        return KafkaSender.create(getSenderOptions());
//    }
//
//    @Bean
//    SenderOptions<String, String> getSenderOptions() {
//        Map<String, Object> producerProps = new HashMap<>();
//        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializerClass);
//        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializerClass);
//
//        return SenderOptions.<String, String>create(producerProps)
//                .maxInFlight(1024);
//    }
//
//    @Bean
//    ReceiverOptions<String, String> getReceiverOptions() {
//        Map<String, Object> consumerProps = new HashMap<>();
//        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupID);
//        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
//        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializerClass);
//        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializerClass);
//
//        return ReceiverOptions.create(consumerProps);
//    }
}
