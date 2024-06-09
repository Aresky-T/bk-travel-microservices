package com.aresky.chatservice.service.conversation;

import com.aresky.chatservice.dto.request.ConversationRequest;
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
    public Mono<List<StaffConversation>> getAll(Integer staffId, Integer limit, Integer offset) {
        String query = "SELECT * FROM conversation WHERE staff_id = :staffId LIMIT :limit OFFSET :offset";
        return databaseClient.sql(query)
                .bind("staffId", staffId)
                .bind("limit", limit)
                .bind("offset", offset)
                .map((row, metadata) -> mapToConversation(row))
                .all()
                .flatMap(conversation -> {
                    StaffConversation empConversation = ConversationMapper.mapToStaffConversation(conversation);
                    Mono<Customer> customerMono = customerService.getById(conversation.getCustomerId());

                    if (conversation.getLatestCustomerMessageId() == null) {
                        return customerMono.map(customer -> {
                            empConversation.setCustomer(CustomerMapper.mapCustomerToCustomerResponse(customer));
                            return empConversation;
                        });
                    }

                    Mono<Message> latestCustomerMessageMono = messageService
                            .getById(conversation.getLatestCustomerMessageId());

                    return Mono.zip(latestCustomerMessageMono, customerMono)
                            .flatMap(tuple -> {
                                empConversation
                                        .setCustomer(CustomerMapper.mapCustomerToCustomerResponse(tuple.getT2()));
                                empConversation.setLatestMessage(MessageMapper.mapToMessageResponse(tuple.getT1()));
                                return Mono.just(empConversation);
                            });
                })
                .collectList();
    }

    @Override
    public Mono<Conversation> update(Conversation conversation) {
        return conversationRepository.existsById(conversation.getId())
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.empty())
                .then(conversationRepository.save(conversation));
    }

    @Override
    public Mono<List<Conversation>> getAllByStaffId(Integer staffId) {
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
                            empConversation.setCustomer(CustomerMapper.mapCustomerToCustomerResponse(customer));
                            return empConversation;
                        });
                    }

                    Mono<Message> latestCustomerMessageMono = messageService
                            .getById(conversation.getLatestCustomerMessageId());
                    return Mono.zip(latestCustomerMessageMono, customerMono)
                            .flatMap(tuple -> {
                                empConversation
                                        .setCustomer(CustomerMapper.mapCustomerToCustomerResponse(tuple.getT2()));
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

                    return staffService.getStaffById(staffId)
                            .flatMap(staff -> {

                                if (staff.getStatus().equals(EActivationStatus.OFFLINE)) {
                                    return Mono.error(new ChatException(ExceptionMessage.STAFF_NOT_ONLINE));
                                }

                                return conversationRepository.existsByCustomerIdAndStaffId(customerId, staffId)
                                        .flatMap(isExists -> {

                                            if (isExists) {
                                                return conversationRepository.findByCustomerIdAndStaffId(customerId,
                                                        staffId);
                                            }

                                            return conversationRepository.save(
                                                    Conversation.builder()
                                                            .customerId(customerId)
                                                            .staffId(staffId)
                                                            .build())
                                                    .then(connect(customerId, staffId));
                                        });
                            });
                });
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

    private Conversation mapToConversation(Row row) {
        return Conversation.builder()
                .id(row.get("id", Integer.class))
                .customerId(row.get("customer_id", Integer.class))
                .staffId(row.get("staff_id", Integer.class))
                .latestCustomerMessageId(row.get("latest_customer_msg_id", Integer.class))
                .latestStaffMessageId(row.get("latest_staff_msg_id", Integer.class))
                .newCustomerMessageCount(row.get("new_customer_msg_count", Integer.class))
                .newStaffMessageCount(row.get("new_staff_msg_count", Integer.class))
                .createdAt(row.get("created_at", ZonedDateTime.class))
                .build();
    }
}
