package com.aresky.chatservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConversationRequest {
    private Integer customerId;
    private Integer staffId;
}
