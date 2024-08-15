package com.aresky.chatservice.dto.response;

import com.aresky.chatservice.entity.Message;
import com.aresky.chatservice.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationMessage {
    private Integer id;
    private String content;
    private String sender;
    private String status;
    private String sentAt;

    public ConversationMessage (Message message){
        this.id = message.getId();
        this.content = message.getContent();;
        this.sender = message.getSender().name();
        this.status = message.getStatus().name();
        this.sentAt = DateUtils.formatDateTime(message.getSentAt());
    }
}
