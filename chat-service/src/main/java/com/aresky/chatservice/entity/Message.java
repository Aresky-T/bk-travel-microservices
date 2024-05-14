package com.aresky.chatservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("message")
public class Message {
    @Id
    @Column("id")
    private Integer id;

    @Column("conversation_id")
    private Integer conversationId;

    @Column("content")
    private String content;

    @Column("sender")
    private EMessageSender sender;

    @Column("status")
    private EMessageStatus status;

    @Column("sent_at")
    private ZonedDateTime sentAt;
}
