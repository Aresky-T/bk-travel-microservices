package com.aresky.tourservice.kafka;

import com.aresky.tourservice.entity.SubTour;
import com.aresky.tourservice.repository.SubTourRepository;
import com.google.common.util.concurrent.AtomicDouble;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class KafkaConsumerEvent {

    @Autowired
    private SubTourRepository subTourRepository;

    @KafkaListener(topics = {
            KafkaTopic.BOOKING_WITH_VNPAY_SUCCESS,
            KafkaTopic.BOOKING_SUCCESS,
            KafkaTopic.BOOKING_CANCEL_APPROVED
    }, groupId = "bk-travel-group")
    public void handleEventListener(ConsumerRecord<String, String> record){

        String topic = record.topic();
        String recordValue = record.value();

        log.info("Tour service received data from kafka: {}", recordValue);
        KafkaMessageType.NotificationRequest data = KafkaMessageType.NotificationRequest.build(recordValue);
        log.info("Data: {}", data);
        AtomicDouble subTourIdAtomic = new AtomicDouble(0);

        if(data != null && data.getKeywords().containsKey("subTourId")){
            subTourIdAtomic.set((Double) data.getKeywords().get("subTourId"));
        }

        int subTourId = subTourIdAtomic.intValue();
        Optional<SubTour> subTourOptional = Optional.empty();

        if(subTourId != 0){
            subTourOptional = subTourRepository.findById(subTourId);
        }

        if(subTourOptional.isPresent()){

            SubTour subTour = subTourOptional.get();
            int availableSeats = subTour.getAvailableSeats();
            int totalSeats = subTour.getTour().getTotalSeats();

            switch (topic){
                case KafkaTopic.BOOKING_SUCCESS, KafkaTopic.BOOKING_WITH_VNPAY_SUCCESS:
                    subTour.setAvailableSeats(Math.max(availableSeats - 1, 0));
                    subTourRepository.save(subTour);
                    log.info("Decrease numbers of availableSeats by 1 for subTourId = {}", subTourId);
                    break;
                case KafkaTopic.BOOKING_CANCEL_APPROVED:
                    subTour.setAvailableSeats(Math.min(availableSeats + 1, totalSeats));
                    subTourRepository.save(subTour);
                    log.info("Increase numbers of availableSeats by 1 for subTourId = {}", subTourId);
                    break;
            }
        }
    }
}
