package com.aresky.chatservice.mappers.http;

import com.aresky.chatservice.dto.response.ConversationForCustomer;
import com.aresky.chatservice.dto.response.ConversationForEmployee;
import com.aresky.chatservice.entity.Conversation;
import com.aresky.chatservice.utils.DateUtils;

public class ConversationMapper {
    public static ConversationForEmployee mapToConversationForEmployee(Conversation conversation) {
        return ConversationForEmployee.builder()
                .id(conversation.getId())
                .createdAt(DateUtils.formatDateTime(conversation.getCreatedAt()))
                .build();
    };

    public static ConversationForCustomer mapToConversationForCustomer(Conversation conversation) {
        return ConversationForCustomer.builder()
                .id(conversation.getId())
                .build();
    };
}
