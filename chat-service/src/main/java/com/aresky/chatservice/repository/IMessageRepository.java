package com.aresky.chatservice.repository;

import com.aresky.chatservice.entity.Message;

import reactor.core.publisher.Flux;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import com.aresky.chatservice.entity.EMessageSender;
import com.aresky.chatservice.entity.EMessageStatus;

public interface IMessageRepository extends R2dbcRepository<Message, Integer> {
    Flux<Message> findAllByConversationId(Integer conversationId);

    Flux<Message> findAllByConversationIdAndSenderAndStatus(Integer conversationId, EMessageSender sender,
            EMessageStatus status);

    Flux<Message> findAllByConversationIdAndSender(Integer conversationId, EMessageSender sender);
}
