package com.aresky.bookingservice.service.statistic;

import com.aresky.bookingservice.model.Booking;
import com.aresky.bookingservice.model.CancellationRequested;
import com.aresky.bookingservice.repository.BookingStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StatisticServiceImp implements IStatisticService {

    @Autowired
    private BookingStatisticRepository statisticRepository;

    @Override
    public Mono<Void> updateStatistics(Booking booking) {
        Integer tourId = booking.getTourId();
        Integer subTourId = booking.getSubTourId();
        Integer month = booking.getBookedTime().getMonthValue();
        Integer year = booking.getBookedTime().getYear();

        return Mono.empty();
    }

    @Override
    public Mono<Void> updateStatistics(CancellationRequested cancellationRequested) {
        return Mono.empty();
    }
}
