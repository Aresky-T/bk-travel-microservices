package com.aresky.mailservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("mail")
public class Mail {

    @Id
    private Integer id;

    @Column("subject")
    private String subject;

    @Column("body")
    private String body;

    @Column("mail_box_id")
    private Integer mailBoxId;

    @Column("status")
    private EMailStatus status;

    @Column("sent_at")
    private ZonedDateTime sentAt;

    @Transient
    private Boolean isReplied;

    @Transient
    private MailReply reply;
}
