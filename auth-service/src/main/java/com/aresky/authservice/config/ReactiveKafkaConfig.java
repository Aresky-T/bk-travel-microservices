package com.aresky.authservice.config;

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
public class ReactiveKafkaConfig {

    @Value("${kafka.bootstrap-servers}")
    private String SERVER;

    // Producer properties
    private final Class<StringSerializer> KEY_SERIALIZER = StringSerializer.class;
    private final Class<StringSerializer> VALUE_SERIALIZER = StringSerializer.class;

    // Consumer properties
    @Value("${kafka.consumer.group-id}")
    private String GROUP_ID;

    @Value("${kafka.consumer.client-id}")
    private String CONSUMER_CLIENT_ID;

    @Value("${kafka.consumer.auto-offset-reset}")
    private String AUTO_OFFSET_RESET;

    private final Class<StringDeserializer> KEY_DESERIALIZER = StringDeserializer.class;
    private final Class<StringDeserializer> VALUE_DESERIALIZER = StringDeserializer.class;

    @Bean
    public KafkaSender<String, String> kafkaSender() {
        return KafkaSender.create(getSenderOptions());
    }

    @Bean
    public SenderOptions<String, String> getSenderOptions() {
        Map<String, Object> producerProps = new HashMap<>();

        // Địa chỉ kafka broker mà producer kết nối tới
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER);

        // ID của producer để định danh producer trên kafka
        producerProps.put(ProducerConfig.CLIENT_ID_CONFIG, "auth-service-producer");

        // Class chuyển đổi key thành byte (tuần tự hoá)
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KEY_SERIALIZER);

        // Class chuyển đổi value thành byte (tuần tự hoá)
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, VALUE_SERIALIZER);

        // Số lần thử gửi lại khi gửi bản ghi thất bại
        producerProps.put(ProducerConfig.RETRIES_CONFIG, 3); // 3 lần

        // Tổng dung lượng bộ nhớ đệm mà producer có thể sử dụng để lưu trữ các bản ghi chưa được gửi.
        producerProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 67108864L); // 64 MB

        // Thời gian tối đa để chờ xác nhận từ broker, tính bằng mili giây.
        // Nếu quá thời gian này mà không nhận được xác nhận, producer sẽ xem như thất bại.
        producerProps.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000); // 30 seconds

        return SenderOptions.<String, String>create(producerProps).maxInFlight(1024);
    }

    @Bean
    public ReceiverOptions<String, String> getReceiverOptions() {
        Map<String, Object> consumerProps = new HashMap<>();

        // Địa chỉ kafka broker mà consumer kết nối tới
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER);

        // ID của nhóm (group) mà consumer thuộc về
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);

        // ID định danh của consumer
        consumerProps.put(ConsumerConfig.CLIENT_ID_CONFIG, CONSUMER_CLIENT_ID);

        // Cấu hình offset để nhận message mới trong partition cho consumer
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_RESET);

        // Class chuyển đổi key từ byte thành object
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KEY_DESERIALIZER);

        // Class chuyển đổi value từ byte thành object
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, VALUE_DESERIALIZER);

        return ReceiverOptions.create(consumerProps);
    }
}
