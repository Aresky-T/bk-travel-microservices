package com.aresky.bookingservice.repository;

import com.aresky.bookingservice.model.CancellationRequested;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CancellationRequestedRepository extends R2dbcRepository<CancellationRequested, Integer> {

}
