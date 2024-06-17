package com.aresky.mailservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailReplyResponse {
    private Integer id;
    private Integer mailId;
    private String subject;
    private String body;
    private String repliedAt;
}
