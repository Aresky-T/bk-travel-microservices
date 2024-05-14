package com.aresky.chatservice.repository;

import com.aresky.chatservice.entity.Conversation;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface IConversationRepository extends R2dbcRepository<Conversation, Integer> {
}
