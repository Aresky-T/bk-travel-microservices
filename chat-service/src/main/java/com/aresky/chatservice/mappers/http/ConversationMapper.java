package com.aresky.chatservice.mappers.http;

import com.aresky.chatservice.dto.response.*;
import com.aresky.chatservice.entity.Conversation;
import com.aresky.chatservice.entity.Message;
import com.aresky.chatservice.utils.DateUtils;

import java.util.List;
import java.util.Optional;

public class ConversationMapper {
    public static StaffConversation mapToStaffConversation(Conversation conversation) {
        return StaffConversation.builder()
                .id(conversation.getId())
                .newCustomerMessageCount(conversation.getNewCustomerMessageCount())
                .createdAt(DateUtils.formatDateTime(conversation.getCreatedAt()))
                .build();
    }

    public static StaffConversation mapToStaffConversation(Conversation conversation, List<Message> messages) {
        return StaffConversation.builder()
                .id(conversation.getId())
                .createdAt(DateUtils.formatDateTime(conversation.getCreatedAt()))
                .build();
    }

    public static CustomerConversation mapToCustomerConversation(Conversation conversation) {
        System.out.println(conversation);
        return CustomerConversation.builder()
                .id(conversation.getId())
                .newStaffMessageCount(conversation.getNewStaffMessageCount())
                .createdAt(DateUtils.formatDateTime(conversation.getCreatedAt()))
                .build();
    }

    public static ConversationResponse mapToConversationResponse(Conversation conversation) {
        ConversationMessage latestMessage = Optional.ofNullable(conversation.getLastestMessage())
                .map(ConversationMessage::new)
                .orElse(null);

        CustomerResponse customer = Optional.ofNullable(conversation.getCustomer())
                .map(CustomerMapper::mapToCustomerResponse)
                .orElse(null);

        return ConversationResponse.builder()
                .id(conversation.getId())
                .newCustomerMessageCount(conversation.getNewCustomerMessageCount())
                .newStaffMessageCount(conversation.getNewStaffMessageCount())
                .createdAt(DateUtils.formatDateTime(conversation.getCreatedAt()))
                .updatedAt(DateUtils.formatDateTime(conversation.getUpdatedAt()))
                .latestMessage(latestMessage)
                .customer(customer)
                .build();
    }
}
