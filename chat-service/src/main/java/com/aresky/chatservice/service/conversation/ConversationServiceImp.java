package com.aresky.chatservice.service.conversation;

import com.aresky.chatservice.dto.request.ConversationRequest;
import com.aresky.chatservice.dto.response.ConversationResponse;
import com.aresky.chatservice.dto.response.CustomerConversation;
import com.aresky.chatservice.dto.response.StaffConversation;
import com.aresky.chatservice.entity.*;
import com.aresky.chatservice.exception.ChatException;
import com.aresky.chatservice.exception.ExceptionMessage;
import com.aresky.chatservice.mappers.http.ConversationMapper;
import com.aresky.chatservice.mappers.http.CustomerMapper;
import com.aresky.chatservice.mappers.http.MessageMapper;
import com.aresky.chatservice.repository.IConversationRepository;
import com.aresky.chatservice.service.customer.ICustomerService;
import com.aresky.chatservice.service.message.IMessageService;
import com.aresky.chatservice.service.staff.IStaffService;

import com.aresky.chatservice.utils.DateUtils;
import io.r2dbc.spi.Row;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ConversationServiceImp implements IConversationService {

    @Autowired
    private IConversationRepository conversationRepository;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IStaffService staffService;

    @Autowired
    private IMessageService messageService;

    @Autowired
    private DatabaseClient databaseClient;

    @Override
    public Mono<List<ConversationResponse>> getAll(Integer staffId, Integer limit, Integer offset) {

        return staffService.existsById(staffId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(ChatException.STAFF_NOT_FOUND_EX))
                .flatMap(existsStaff -> {
                    String query = "SELECT * FROM conversation WHERE staff_id = :staffId ORDER BY updated_at DESC LIMIT :limit OFFSET :offset";
                    return databaseClient.sql(query)
                            .bind("staffId", staffId)
                            .bind("limit", limit)
                            .bind("offset", offset)
                            .map((row, metadata) -> mapToConversation(row))
                            .all()
                            .flatMap(conversation -> {
                                Mono<Customer> customerMono = customerService.getById(conversation.getCustomerId());

                                if(conversation.getLatestMessageId() == null){
                                    return customerMono.map(conversation::customer);
                                }

                                Mono<Message> latestMessageMono = messageService.getById(conversation.getLatestMessageId());
                                return Mono.zip(latestMessageMono, customerMono)
                                        .map(tuple -> conversation.customer(tuple.getT2()).latestMessage(tuple.getT1()));
                            })
                            .map(ConversationMapper::mapToConversationResponse)
                            .collectList();
                });
    }

    @Override
    public Mono<Conversation> save(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    @Override
    public Mono<Conversation> update(Conversation conversation) {
        return conversationRepository.existsById(conversation.getId())
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.empty())
                .then(conversationRepository.save(conversation));
    }

    @Override
    public Mono<Conversation> update(Conversation conversation, Message newMessage) {
        return Mono.fromCallable(() -> {
            EMessageSender sender = newMessage.getSender();

            if(sender.equals(EMessageSender.CUSTOMER)){
                conversation.setNewCustomerMessageCount(conversation.getNewCustomerMessageCount() + 1);
                conversation.setLatestCustomerMessageId(newMessage.getId());
            } else {
                conversation.setNewStaffMessageCount(conversation.getNewStaffMessageCount() + 1);
                conversation.setLatestStaffMessageId(newMessage.getId());
            }

            conversation.setLatestMessageId(newMessage.getId());
            conversation.setUpdatedAt(DateUtils.now());
            return conversation;
        }).flatMap(conversationRepository::save);
    }

    @Override
    public Mono<List<Conversation>> getAllWithMore(Integer staffId) {
        return conversationRepository.findByStaffId(staffId)
                .collectList();
    }

    @Override
    public Mono<CustomerConversation> connectByCustomer(ConversationRequest request) {
        return connect(request.getCustomerId(), request.getStaffId())
                .map(ConversationMapper::mapToCustomerConversation);
    }

    @Override
    public Mono<StaffConversation> connectByStaff(ConversationRequest request) {
        return connect(request.getCustomerId(), request.getStaffId())
                .flatMap(conversation -> {
                    StaffConversation empConversation = ConversationMapper.mapToStaffConversation(conversation);
                    Mono<Customer> customerMono = customerService.getById(conversation.getCustomerId());

                    if (conversation.getLatestCustomerMessageId() == null) {
                        return customerMono.map(customer -> {
                            empConversation.setCustomer(CustomerMapper.mapToCustomerResponse(customer));
                            return empConversation;
                        });
                    }

                    Mono<Message> latestCustomerMessageMono = messageService
                            .getById(conversation.getLatestCustomerMessageId());
                    return Mono.zip(latestCustomerMessageMono, customerMono)
                            .flatMap(tuple -> {
                                empConversation
                                        .setCustomer(CustomerMapper.mapToCustomerResponse(tuple.getT2()));
                                empConversation.setLatestMessage(MessageMapper.mapToMessageResponse(tuple.getT1()));
                                return Mono.just(empConversation);
                            });
                });
    }

    @Transactional
    @Override
    public Mono<Conversation> connect(Integer customerId, Integer staffId) {
        return Mono.zip(customerService.existsById(customerId), staffService.existsById(staffId))
                .flatMap(tuple -> {
                    boolean existsCustomer = tuple.getT1();
                    boolean existsStaff = tuple.getT2();

                    if (!existsCustomer) {
                        return Mono.error(new ChatException(ExceptionMessage.INVALID_CUSTOMER_ID));
                    }

                    if (!existsStaff) {
                        return Mono.error(new ChatException(ExceptionMessage.INVALID_STAFF_ID));
                    }

                    return staffService.getById(staffId);
                })
                .filter(staff -> staff.getStatus().equals(EActivationStatus.ONLINE))
                .switchIfEmpty(Mono.error(new ChatException(ExceptionMessage.STAFF_NOT_ONLINE)))
                .then(customerService.updateActivationStatus(customerId, EActivationStatus.ONLINE))
                .then(conversationRepository.existsByCustomerIdAndStaffId(customerId, staffId))
                .flatMap(existsConversation -> existsConversation
                        ? findByCustomerIdAndStaffId(customerId, staffId)
                        : create(customerId, staffId));
    }

    @Override
    public Mono<Conversation> getById(Integer conversationId) {
        return conversationRepository.findById(conversationId)
                .switchIfEmpty(Mono.error(new ChatException(ExceptionMessage.INVALID_CONVERSATION_ID)));
    }

    @Override
    public Mono<Conversation> getByCustomerIdAndStaffId(Integer customerId, Integer staffId) {
        return conversationRepository.findByCustomerIdAndStaffId(customerId, staffId);
    }

    @Override
    public Mono<Boolean> checkExistsById(Integer conversationId) {
        return conversationRepository.existsById(conversationId);
    }

    @Transactional
    @Override
    public Mono<Void> deleteById(Integer conversationId) {
        return conversationRepository.existsById(conversationId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new ChatException(ExceptionMessage.INVALID_CONVERSATION_ID)))
                .then(conversationRepository.deleteById(conversationId))
                .then();
    }

    private Mono<Conversation> findByCustomerIdAndStaffId(Integer customerId, Integer staffId){
        return conversationRepository.findByCustomerIdAndStaffId(customerId, staffId);
    }

    private Mono<Conversation> create(Integer customerId, Integer staffId){
        Conversation conversation = Conversation.builder()
                .customerId(customerId)
                .staffId(staffId)
                .createdAt(DateUtils.now())
                .build();
        return  conversationRepository.save(conversation);
    }

    private Conversation mapToConversation(Row row) {
        return Conversation.builder()
                .id(row.get("id", Integer.class))
                .customerId(row.get("customer_id", Integer.class))
                .staffId(row.get("staff_id", Integer.class))
                .latestMessageId(row.get("latest_msg_id", Integer.class))
                .latestCustomerMessageId(row.get("latest_customer_msg_id", Integer.class))
                .latestStaffMessageId(row.get("latest_staff_msg_id", Integer.class))
                .newCustomerMessageCount(row.get("new_customer_msg_count", Integer.class))
                .newStaffMessageCount(row.get("new_staff_msg_count", Integer.class))
                .createdAt(row.get("created_at", ZonedDateTime.class))
                .updatedAt(row.get("updated_at", ZonedDateTime.class))
                .build();
    }
}
