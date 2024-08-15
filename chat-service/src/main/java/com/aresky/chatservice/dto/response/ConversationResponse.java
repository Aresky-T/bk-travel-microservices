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
    private Integer newCustomerMessageCount;
    private Integer newStaffMessageCount;
    private String createdAt;
    private String updatedAt;
    private ConversationMessage latestMessage;
    private CustomerResponse customer;
}
