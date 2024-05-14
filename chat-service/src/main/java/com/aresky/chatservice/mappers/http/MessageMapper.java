package com.aresky.chatservice.mappers.http;

import com.aresky.chatservice.dto.request.MessageRequest;
import com.aresky.chatservice.dto.response.MessageResponse;
import com.aresky.chatservice.entity.EMessageStatus;
import com.aresky.chatservice.entity.Message;
import com.aresky.chatservice.utils.DateUtils;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class MessageMapper {
    public static Message mapToMessage(MessageRequest input){
        return Message.builder()
               .conversationId(input.getConversationId())
               .content(input.getContent())
               .sender(input.getSender())
               .status(EMessageStatus.NEW)
               .sentAt(ZonedDateTime.now(ZoneId.systemDefault()))
               .build();
    }

    public static MessageResponse mapToMessageResponse(Message message){
        return MessageResponse.builder()
               .id(message.getId())
               .content(message.getContent())
               .sender(message.getSender().name())
               .status(message.getStatus().name())
               .sentAt(DateUtils.formatDate(message.getSentAt().toLocalDate()))
               .build();
    }
}
