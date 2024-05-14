package com.aresky.chatservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationForCustomer {
    private Integer id;
    private CustomerResponse customer;
    private StaffResponse staff;
    private List<MessageResponse> chatList;
}
