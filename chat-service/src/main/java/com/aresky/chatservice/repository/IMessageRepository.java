package com.aresky.chatservice.repository;

import com.aresky.chatservice.entity.Message;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface IMessageRepository extends R2dbcRepository<Message, Integer> {
}
