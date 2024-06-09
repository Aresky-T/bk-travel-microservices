package com.aresky.chatservice.service.message;

import com.aresky.chatservice.dto.request.MessageRequest;
import com.aresky.chatservice.dto.response.MessageResponse;
import com.aresky.chatservice.entity.EMessageSender;
import com.aresky.chatservice.entity.EMessageStatus;
import com.aresky.chatservice.entity.Message;
import com.aresky.chatservice.mappers.http.MessageMapper;
import com.aresky.chatservice.repository.IMessageRepository;

import io.r2dbc.spi.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class MessageServiceImp implements IMessageService {
    @Autowired
    private IMessageRepository messageRepository;

    @Autowired
    private DatabaseClient databaseClient;

    @Transactional
    @Override
    public Mono<Message> save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Mono<Message> create(Integer conversationId, MessageRequest dto) {
        return Mono.just(dto)
                .map(MessageMapper::mapToMessage)
                .flatMap(message -> {
                    message.setConversationId(conversationId);
                    return messageRepository.save(message);
                });
    }

    @Override
    public Mono<List<Message>> saveAll(List<Message> messages) {
        return messageRepository.saveAll(messages).collectList();
    }

    @Override
    public Mono<List<Message>> getAllBy(Integer conversationId) {
        return messageRepository.findAllByConversationId(conversationId).collectList();
    }

    @Override
    public Mono<List<MessageResponse>> getAllMessageResponses(Integer conversationId, Integer limit, Integer offset) {
        String query = "SELECT * FROM message WHERE conversation_id = :conversationId ORDER BY sent_at DESC LIMIT :limit OFFSET :offset;";

        return databaseClient.sql(query)
                .bind("conversationId", conversationId)
                .bind("limit", limit)
                .bind("offset", offset)
                .map((row, metadata) -> mapRowToMessage(row))
                .all()
                .map(MessageMapper::mapToMessageResponse)
                .collectList();
    }

    @Override
    public Mono<List<Message>> getAllBy(Integer conversationId, EMessageSender sender,
            EMessageStatus status) {
        return messageRepository.findAllByConversationIdAndSenderAndStatus(conversationId, sender, status)
                .collectList();
    }

    @Override
    public Mono<List<Message>> seenMessages(Integer conversationId, EMessageSender sender) {
        return messageRepository.findAllByConversationIdAndSender(conversationId, sender).collectList();
    }

    @Override
    public Mono<Message> getById(Integer id) {
        return Mono.just(id)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.empty())
                .flatMap(existsId -> messageRepository.findById(existsId));
    }

    @Override
    public Mono<Message> getById(Mono<Integer> id) {
        return id.switchIfEmpty(Mono.empty())
                .flatMap(idValue -> messageRepository.findById(idValue));
    }

    @Override
    public Mono<Void> delete(Integer chatId) {
        return messageRepository.deleteById(chatId);
    }

    private Message mapRowToMessage(Row row){
        return Message.builder()
                .id(row.get("id", Integer.class))
                .conversationId(row.get("conversation_id", Integer.class))
                .sender(EMessageSender.valueOf(row.get("sender", String.class)))
                .content(row.get("content", String.class))
                .status(EMessageStatus.valueOf(row.get("status", String.class)))
                .sentAt(row.get("sent_at", ZonedDateTime.class))
                .build();
    }
}
