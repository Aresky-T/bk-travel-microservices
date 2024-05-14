package com.aresky.chatservice.service.conversation;

import com.aresky.chatservice.entity.Conversation;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ConversationServiceImp implements IConversationService{
    @Override
    public Mono<List<Conversation>> findAllByStaffId(int staffId) {
        return null;
    }

    @Override
    public Mono<Conversation> connect(String fullName, String email) {
        return null;
    }

    @Override
    public Mono<Conversation> connect(int accountId) {
        return null;
    }

    @Override
    public Mono<Conversation> findBy(int conversationId) {
        return null;
    }

    @Override
    public Mono<Conversation> findBy(int customerId, int staffId) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(int conversationId) {
        return null;
    }
}
