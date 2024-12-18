package com.aresky.tourservice.kafka;

import com.aresky.tourservice.entity.SubTour;
import com.aresky.tourservice.repository.SubTourRepository;
import com.google.common.util.concurrent.AtomicDouble;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;
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
    }, containerFactory = "kafkaListenerContainerFactory")
    public void handleEventListener(ConsumerRecord<String, String> record){

        String topic = record.topic();
        String recordValue = record.value();

        log.info("Tour service received data from topic: {}", topic);
        log.info("Received data: {}", recordValue);

        KafkaMessageType.NotificationRequest data =
                Optional.ofNullable(KafkaMessageType.NotificationRequest.build(recordValue))
                        .orElse(new KafkaMessageType.NotificationRequest());
        log.info("Converted data: {}", data);

        Map<String, Object> keywordsFromData = data.getKeywords();

        AtomicDouble subTourIdAtomic = new AtomicDouble(0);
        AtomicDouble totalTouristsOfNewBookingAtomic = new AtomicDouble(0);

        if(keywordsFromData != null && keywordsFromData.containsKey("subTourId")){
            subTourIdAtomic.set((Double) keywordsFromData.get("subTourId"));
        }

        if(keywordsFromData != null && keywordsFromData.containsKey("totalTourists")){
            totalTouristsOfNewBookingAtomic.set((Double) keywordsFromData.get("totalTourists"));
        }

        int subTourId = subTourIdAtomic.intValue();
        int totalTourists = totalTouristsOfNewBookingAtomic.intValue();
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
                {
                    int newAvailableSeats = availableSeats - totalTourists;
                    subTour.setAvailableSeats(Math.max(newAvailableSeats, 0));
                    subTourRepository.save(subTour);
                    log.info("Decreased numbers of availableSeats for subTourId = {} to {}", subTourId, totalTourists);
                    break;
                }
                case KafkaTopic.BOOKING_CANCEL_APPROVED:
                {
                    int newAvailableSeats = availableSeats + totalTourists;
                    subTour.setAvailableSeats(Math.min(newAvailableSeats, totalSeats));
                    subTourRepository.save(subTour);
                    log.info("Increase numbers of availableSeats for subTourId = {} to {}", subTourId, totalTourists);
                    break;
                }

            }
        }
    }
}
