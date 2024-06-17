package com.aresky.mailservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailReplyRequest {
    private String subject;
    private String body;
    private Integer mailId;
}
