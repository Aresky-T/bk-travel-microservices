package com.aresky.chatservice.service.conversation;

import com.aresky.chatservice.dto.request.ConversationRequest;
import com.aresky.chatservice.dto.response.CustomerConversation;
import com.aresky.chatservice.dto.response.StaffConversation;
import com.aresky.chatservice.entity.Conversation;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IConversationService {
    Mono<Conversation> update(Conversation conversation);

    Mono<List<Conversation>> getAllByStaffId(Integer staffId);

    Mono<List<StaffConversation>> getAll(Integer staffId, Integer limit, Integer offset);

    Mono<CustomerConversation> connectByCustomer(ConversationRequest request);

    Mono<StaffConversation> connectByStaff(ConversationRequest request);

    Mono<Conversation> connect(Integer customerId, Integer staffId);

    Mono<Conversation> getById(Integer conversationId);

    Mono<Conversation> getByCustomerIdAndStaffId(Integer customerId, Integer staffId);

    Mono<Boolean> checkExistsById(Integer conversationId);

    Mono<Void> deleteById(Integer conversationId);
}
