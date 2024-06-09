package com.aresky.chatservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerConversation {
    private Integer id;
    private Integer newStaffMessageCount;
    private MessageResponse latestStaffMessage;
    private String createdAt;
}
