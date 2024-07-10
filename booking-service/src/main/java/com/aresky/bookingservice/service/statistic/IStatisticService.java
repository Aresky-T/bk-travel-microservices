package com.aresky.bookingservice.service.statistic;

import com.aresky.bookingservice.model.Booking;
import com.aresky.bookingservice.model.CancellationRequested;
import reactor.core.publisher.Mono;

public interface IStatisticService {
    Mono<Void> updateStatistics(Booking booking);
    Mono<Void> updateStatistics(CancellationRequested cancellationRequested);
}
