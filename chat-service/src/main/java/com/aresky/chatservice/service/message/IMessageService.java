package com.aresky.chatservice.service.message;

import com.aresky.chatservice.dto.request.MessageRequest;
import com.aresky.chatservice.entity.Message;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IMessageService {
    Mono<Message> save(Message message);
    Mono<Message> create(MessageRequest dto);
    Mono<List<Message>> saveAll(List<Message> messages);
    Mono<List<Message>> findAllByConversationId(int conversationId);
    Mono<Void> delete(int chatId);
}
