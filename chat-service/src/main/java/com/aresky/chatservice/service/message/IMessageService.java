package com.aresky.chatservice.service.message;

import com.aresky.chatservice.dto.request.MessageRequest;
import com.aresky.chatservice.dto.response.MessageResponse;
import com.aresky.chatservice.entity.EMessageSender;
import com.aresky.chatservice.entity.EMessageStatus;
import com.aresky.chatservice.entity.Message;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IMessageService {
    Mono<Message> save(Message message);

    Mono<Message> create(Integer conversationId, MessageRequest dto);

    Mono<List<Message>> saveAll(List<Message> messages);

    Mono<List<Message>> getAllBy(Integer conversationId);

    Mono<List<MessageResponse>> getAllMessageResponses(Integer conversationId, Integer limit, Integer offset);

    Mono<List<Message>> getAllBy(Integer conversationId, EMessageSender sender, EMessageStatus status);

    Mono<Message> getById(Integer id);

    Mono<Message> getById(Mono<Integer> id);

    Mono<List<Message>> seenMessages(Integer conversationId, EMessageSender sender);

    Mono<Void> delete(Integer chatId);
}
