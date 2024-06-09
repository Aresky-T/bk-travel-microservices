package com.aresky.chatservice.repository;

import com.aresky.chatservice.entity.Conversation;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IConversationRepository extends R2dbcRepository<Conversation, Integer> {
    Mono<Boolean> existsByCustomerIdAndStaffId(Integer customerId, Integer staffId);

    Mono<Conversation> findByCustomerIdAndStaffId(Integer customerId, Integer staffId);

    Flux<Conversation> findByStaffId(Integer staffId);
}
