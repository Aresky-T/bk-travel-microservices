package com.aresky.chatservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffConversation {
    private Integer id;
    private CustomerResponse customer;
    private Integer newCustomerMessageCount;
    private MessageResponse latestMessage;
    private String createdAt;
}
