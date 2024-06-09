package com.aresky.chatservice.dto.request;

import com.aresky.chatservice.entity.EMessageSender;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageRequest {
    private String content;
    private EMessageSender sender;
    private Integer senderId;
}
