package com.aresky.mailservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffMailDetails {
    private Integer id;
    private Integer boxId;
    private String subject;
    private String body;
    private String status;
    private String sentAt;
    private CustomerResponse customer;
    private MailReplyResponse reply;
}
