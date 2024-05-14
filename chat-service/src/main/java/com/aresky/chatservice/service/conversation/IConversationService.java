package com.aresky.chatservice.service.conversation;

import com.aresky.chatservice.entity.Conversation;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IConversationService {
    Mono<List<Conversation>> findAllByStaffId(int staffId);
    Mono<Conversation> connect(String fullName, String email);
    Mono<Conversation> connect(int accountId);
    Mono<Conversation> findBy(int conversationId);
    Mono<Conversation> findBy(int customerId, int staffId);
    Mono<Void> deleteById(int conversationId);
}
