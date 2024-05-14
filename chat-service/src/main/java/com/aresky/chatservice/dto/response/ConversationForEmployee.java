package com.aresky.chatservice.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationForEmployee {
    private Integer id;
    private String createdAt;
    private CustomerResponse customer;
    private Integer newMessagesCount;
    private MessageResponse latestMessage;
    private List<MessageResponse> messageList;
}
