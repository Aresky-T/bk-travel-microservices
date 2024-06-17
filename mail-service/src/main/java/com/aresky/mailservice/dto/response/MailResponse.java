package com.aresky.mailservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailResponse {
    private Integer id;
    private String subject;
    private String body;
    private String sentAt;
    private String status;
    private Boolean isReplied;
}
