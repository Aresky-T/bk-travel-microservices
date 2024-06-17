package com.aresky.mailservice.repository;

import com.aresky.mailservice.entity.MailBox;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IMailBoxRepository extends R2dbcRepository<MailBox, Integer> {
    Mono<Boolean> existsByCustomerIdAndStaffId(Integer customerId, Integer staffId);
    Mono<MailBox> findByCustomerIdAndStaffId(Integer customerId, Integer staffId);
    Flux<MailBox> findByStaffId(Integer staffId);
}
