package com.aresky.chatservice.controller;

import com.aresky.chatservice.dto.request.MessageRequest;
import com.aresky.chatservice.dto.response.ConversationMessage;
import com.aresky.chatservice.dto.response.ConversationResponse;
import com.aresky.chatservice.entity.*;
import com.aresky.chatservice.mappers.http.ConversationMapper;
import com.aresky.chatservice.service.conversation.IConversationService;
import com.aresky.chatservice.service.customer.ICustomerService;
import com.aresky.chatservice.service.message.IMessageService;
import com.aresky.chatservice.service.staff.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Controller
public class WebSocketController {

    @Autowired
    private IConversationService conversationService;

    @Autowired
    private IMessageService messageService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IStaffService staffService;

    @SendTo("/topic/conversation/{conversationId}")
    @MessageMapping("/chat/send-message-to-conversation/{conversationId}")
    public Mono<ConversationResponse> sendMessageToConversation(
            @DestinationVariable("conversationId") Integer conversationId,
            @Payload MessageRequest newMessageRequest) {
        return conversationService.getById(conversationId)
                .onErrorResume(err -> Mono.empty())
                .flatMap(conversation -> {
                    Mono<Message> messageMono = messageService.create(conversationId, newMessageRequest);
                    Mono<Customer> customerMono = customerService.getById(conversation.getCustomerId());
                    return Mono.zip(Mono.just(conversation), messageMono, customerMono);
                })
                .flatMap(tuple -> conversationService.update(tuple.getT1(), tuple.getT2())
                        .map(updatedConversation -> updatedConversation
                                .latestMessage(tuple.getT2()).customer(tuple.getT3())))
                .onErrorResume(err -> Mono.empty())
                .map(ConversationMapper::mapToConversationResponse);
    }

    @SendTo("/topic/conversation/{conversationId}")
    @MessageMapping("/chat/send-message-to-customer/{conversationId}")
    public Mono<ConversationMessage> sendMessageToCustomer(
            @DestinationVariable("conversationId") Integer conversationId,
            @Payload MessageRequest messageRequest) {

        if (!messageRequest.getSender().equals(EMessageSender.STAFF)) {
            return Mono.empty();
        }

        return conversationService.getById(conversationId)
                .onErrorResume(err -> Mono.empty())
                .filter(con -> messageRequest.getSenderId().equals(con.getStaffId()))
                .zipWith(messageService.create(conversationId, messageRequest))
                .flatMap(tuple -> conversationService.update(tuple.getT1(), tuple.getT2())
                            .thenReturn(new ConversationMessage(tuple.getT2())));
    }

    @SendTo("/topic/staff/{staffId}")
    @MessageMapping("/chat/send-message-to-staff/{staffId}")
    public Mono<ConversationResponse> sendMessageToStaff(
            @DestinationVariable("staffId") Integer staffId,
            @Payload MessageRequest newMessageRequest) {

        if(!newMessageRequest.getSender().equals(EMessageSender.CUSTOMER)){
            return Mono.empty();
        }

        return Mono.zip(customerService.getById(newMessageRequest.getSenderId()), staffService.getById(staffId))
                .flatMap(tuple -> {
                    Customer customer = tuple.getT1();
                    Staff staff = tuple.getT2();

                    return conversationService.getByCustomerIdAndStaffId(customer.getId(), staff.getId())
                            .flatMap(conversation -> messageService.create(conversation.getId(), newMessageRequest)
                                    .flatMap(newMessage -> conversationService.update(conversation, newMessage)
                                            .doOnNext(updatedConversation -> updatedConversation
                                                    .customer(customer)
                                                    .staff(staff)
                                                    .latestMessage(newMessage)
                                            )
                                    )
                            );
                })
                .onErrorResume(err -> Mono.empty())
                .map(ConversationMapper::mapToConversationResponse);
    }

    @SendTo("/topic/conversation/{conversationId}")
    @MessageMapping("/chat/read-customer-messages/conversation/{conversationId}")
    public Mono<ConversationResponse> readConversationMessages(@DestinationVariable Integer conversationId){
        return conversationService.getById(conversationId)
                .onErrorResume(err -> Mono.empty())
                .flatMap(conversation -> {
                    conversation.setNewCustomerMessageCount(0);
                    return messageService.seenMessages(conversationId, EMessageSender.CUSTOMER)
                            .thenReturn(conversation);
                })
//                .doOnNext(conversation -> conversation.setNewCustomerMessageCount(0))
//                .doOnNext(conversation -> messageService.seenMessages(conversationId, EMessageSender.CUSTOMER))
                .flatMap(conversationService::save)
                .onErrorResume(err -> Mono.empty())
                .flatMap(conversation -> {
                    Mono<Customer> customerMono = customerService.getById(conversation.getCustomerId());
                    Mono<Message> latestMessageMono = messageService.getLatestByConversationId(conversationId);

                    return Mono.zip(customerMono, latestMessageMono)
                            .map(tuple -> conversation.customer(tuple.getT1()).latestMessage(tuple.getT2()));
                })
                .map(ConversationMapper::mapToConversationResponse);
    }

    @SendTo("/topic/staff/status/{staffId}")
    @MessageMapping("/staff/{staffId}/update-status/{status}")
    public Mono<EActivationStatus> updateStaffStatus(
            @DestinationVariable("staffId") int staffId,
            @DestinationVariable("status") String status,
            SimpMessageHeaderAccessor headerAccessor
    ){
        EActivationStatus statusEnum = EActivationStatus.valueOf(status);
        return staffService.updateStatus(staffId, statusEnum)
                .flatMap((staff) -> addAttribute(headerAccessor, "staffId", staffId)
                            .thenReturn(staff.getStatus()));
    }

    @SendTo("/topic/customer/status/{customerId}")
    @MessageMapping("/customer/{customerId}/update-status/{status}")
    public Mono<EActivationStatus> updateCustomerStatus(
            @DestinationVariable("customerId") int customerId,
            @DestinationVariable("status") String status,
            SimpMessageHeaderAccessor headerAccessor
    ){
        return Mono.just(EActivationStatus.valueOf(status))
                .onErrorResume(IllegalArgumentException.class, err -> Mono.empty())
                .flatMap(statusEnum -> customerService.updateActivationStatus(customerId, statusEnum))
                .flatMap(customer -> addAttribute(headerAccessor, "customerId", customerId)
                            .thenReturn(customer.getStatus()));
    }

    @SendTo("/topic/staff/{staffId}")
    @MessageMapping("/customer/{customerId}/staff/{staffId}/connect")
    public Mono<ConversationResponse> connectConversation(
            @DestinationVariable("customerId") Integer customerId,
            @DestinationVariable("staffId") Integer staffId,
            SimpMessageHeaderAccessor headerAccessor
    ){
        Mono<Customer> customerMono = customerService.updateActivationStatus(customerId, EActivationStatus.ONLINE);
        Mono<Conversation> conversationMono = conversationService.getByCustomerIdAndStaffId(customerId, staffId)
                .flatMap(conversation -> messageService.getLatestByConversationId(conversation.getId())
                        .map(conversation::latestMessage));

        return conversationMono.flatMap(conversation ->
                        addAttribute(headerAccessor, "customerId", customerId)
                                .then(Mono.zip(conversationMono, customerMono)))
                .map(tuple -> tuple.getT1().customer(tuple.getT2()))
                .map(ConversationMapper::mapToConversationResponse);
    }

    @SendTo("/topic/staff/{staffId}")
    @MessageMapping("/customer/{customerId}/staff/{staffId}/disconnect")
    public Mono<ConversationResponse> disconnectConversation(
            @DestinationVariable("customerId") Integer customerId,
            @DestinationVariable("staffId") Integer staffId
    ){
        Mono<Customer> customerMono = customerService.updateActivationStatus(customerId, EActivationStatus.OFFLINE);
        Mono<Conversation> conversationMono = conversationService.getByCustomerIdAndStaffId(customerId, staffId)
                .flatMap(conversation -> messageService.getLatestByConversationId(conversation.getId())
                        .map(conversation::latestMessage));

        return Mono.zip(conversationMono, customerMono)
                .map(tuple -> tuple.getT1().customer(tuple.getT2()))
                .map(ConversationMapper::mapToConversationResponse);
    }

    private Mono<Void> addAttribute(SimpMessageHeaderAccessor headerAccessor, String key, Object value){
        return Mono.justOrEmpty(Objects.requireNonNull(headerAccessor.getSessionAttributes()))
                .onErrorResume(err -> Mono.empty())
                .doOnNext(attributes -> attributes.put(key, value))
                .then();
    }
}
