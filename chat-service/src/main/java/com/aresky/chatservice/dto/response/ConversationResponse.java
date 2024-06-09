package com.aresky.chatservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationResponse {
    private Integer id;
    private Integer customerId;
    private Integer staffId;
    private Integer newCustomerMessageCount;
    private Integer newStaffMessageCount;
    private MessageResponse latestMessage;
    private String createdAt;
}
