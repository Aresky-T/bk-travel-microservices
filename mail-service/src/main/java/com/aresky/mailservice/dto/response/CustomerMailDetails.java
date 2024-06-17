package com.aresky.mailservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerMailDetails {
    private Integer id;
    private String subject;
    private String body;
    private String sentAt;
    private StaffResponse staff;
    private MailReplyResponse reply;
}
