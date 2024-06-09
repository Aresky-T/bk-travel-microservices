package com.aresky.chatservice.controller;

import com.aresky.chatservice.dto.request.MessageRequest;
import com.aresky.chatservice.dto.response.ConversationResponse;
import com.aresky.chatservice.dto.response.MessageResponse;
import com.aresky.chatservice.entity.Customer;
import com.aresky.chatservice.entity.EMessageSender;
import com.aresky.chatservice.entity.Staff;
import com.aresky.chatservice.mappers.http.ConversationMapper;
import com.aresky.chatservice.mappers.http.MessageMapper;
import com.aresky.chatservice.service.conversation.IConversationService;
import com.aresky.chatservice.service.customer.ICustomerService;
import com.aresky.chatservice.service.message.IMessageService;
import com.aresky.chatservice.service.staff.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

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
                .switchIfEmpty(Mono.empty())
                .flatMap(conversation -> {
                    if (!newMessageRequest.getSender().equals(EMessageSender.CUSTOMER)) {
                        return Mono.empty();
                    }

                    if (!newMessageRequest.getSenderId().equals(conversation.getCustomerId())) {
                        return Mono.empty();
                    }

                    return messageService.create(conversationId, newMessageRequest)
                            .flatMap(newMsg -> {
                                conversation.setLatestCustomerMessageId(newMsg.getId());
                                conversation.setNewCustomerMessageCount(conversation.getNewCustomerMessageCount() + 1);

                                return conversationService.update(conversation)
                                        .map(updatedConversation -> {
                                            MessageResponse messageResponse = MessageMapper
                                                    .mapToMessageResponse(newMsg);
                                            ConversationResponse response = ConversationMapper
                                                    .mapToConversationResponse(updatedConversation);
                                            response.setLatestMessage(messageResponse);
                                            return response;
                                        });
                            });
                });
    }

    @SendTo("/topic/conversation/{conversationId}")
    @MessageMapping("/chat/send-message-to-customer/{conversationId}")
    public Mono<ConversationResponse> sendMessageToCustomer(
            @DestinationVariable("conversationId") Integer conversationId,
            @Payload MessageRequest newMessageRequest) {
        return conversationService.getById(conversationId)
                .switchIfEmpty(Mono.empty())
                .flatMap(conversation -> {
                    if (!newMessageRequest.getSender().equals(EMessageSender.STAFF)) {
                        return Mono.empty();
                    }

                    if (!newMessageRequest.getSenderId().equals(conversation.getStaffId())) {
                        return Mono.empty();
                    }

                    return messageService.create(conversationId, newMessageRequest)
                            .flatMap(newMsg -> {
                                conversation.setLatestStaffMessageId(newMsg.getId());
                                conversation.setNewStaffMessageCount(conversation.getNewStaffMessageCount() + 1);

                                return conversationService.update(conversation)
                                        .map(updatedConversation -> {
                                            MessageResponse messageResponse = MessageMapper
                                                    .mapToMessageResponse(newMsg);
                                            ConversationResponse response = ConversationMapper
                                                    .mapToConversationResponse(updatedConversation);
                                            response.setLatestMessage(messageResponse);
                                            return response;
                                        });
                            });
                });
    }

    @SendTo("/topic/staff/{staffId}")
    @MessageMapping("/chat/send-message-to-staff/{staffId}")
    public Mono<ConversationResponse> sendMessageToStaff(
            @DestinationVariable("staffId") Integer staffId,
            @Payload MessageRequest newMessageRequest) {
        return Mono.just(newMessageRequest.getSender().equals(EMessageSender.CUSTOMER))
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.empty())
                .then(Mono.zip(
                        customerService.getById(newMessageRequest.getSenderId()),
                        staffService.getStaffById(staffId)))
                .flatMap(tuple -> {
                    Customer customer = tuple.getT1();
                    Staff staff = tuple.getT2();

                    return conversationService.getByCustomerIdAndStaffId(customer.getId(), staff.getId())
                            .switchIfEmpty(Mono.empty())
                            .flatMap(conversation -> messageService.create(conversation.getId(), newMessageRequest)
                                    .flatMap(newMsg -> {
                                        conversation.setLatestCustomerMessageId(newMsg.getId());
                                        conversation.setNewCustomerMessageCount(
                                                conversation.getNewCustomerMessageCount() + 1);

                                        return conversationService.update(conversation)
                                                .map(updatedConversation -> {

                                                    ConversationResponse response = ConversationMapper
                                                            .mapToConversationResponse(updatedConversation);

                                                    response.setLatestMessage(
                                                            MessageMapper.mapToMessageResponse(newMsg));

                                                    return response;
                                                });
                                    }));
                });
    }
    // @SendTo("/topic/conversation/{conversationId}")
    // @MessageMapping("/chat/view-message")
    // public Mono<ConversationResponse> viewMessages(
    // @DestinationVariable("conversationId") Integer conversationId,
    // @Payload String viewer
    // ){
    //
    // }
}
