package com.aresky.staffservice.repository;

import com.aresky.staffservice.model.Status;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface IStatusRepository extends R2dbcRepository<Status, Integer> {
}
