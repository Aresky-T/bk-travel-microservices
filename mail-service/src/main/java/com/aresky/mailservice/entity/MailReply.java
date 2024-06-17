package com.aresky.mailservice.entity;

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
@Table("mail_reply")
public class MailReply {

    @Id
    private Integer id;

    @Column("mail_id")
    private Integer mailId;

    @Column("subject")
    private String subject;

    @Column("body")
    private String body;

    @Column("replied_at")
    private ZonedDateTime repliedAt;
}
