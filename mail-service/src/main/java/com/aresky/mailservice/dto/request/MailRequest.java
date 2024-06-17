package com.aresky.mailservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailRequest {
    private String fullName;
    private String email;
    private String subject;
    private String body;
}
