package com.aresky.chatservice.service.message;

import com.aresky.chatservice.dto.request.MessageRequest;
import com.aresky.chatservice.entity.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MessageServiceImp implements IMessageService {

    @Override
    public Mono<Message> save(Message message) {
        return null;
    }

    @Override
    public Mono<Message> create(MessageRequest dto) {
        return null;
    }

    @Override
    public Mono<List<Message>> saveAll(List<Message> messages) {
        return null;
    }

    @Override
    public Mono<List<Message>> findAllByConversationId(int conversationId) {
        return null;
    }

    @Override
    public Mono<Void> delete(int chatId) {
        return null;
    }
}
