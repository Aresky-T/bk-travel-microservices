package com.aresky.mailservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailBoxDetails {
    private Integer id;
    private CustomerResponse customer;
    private Integer totalMail;
    private Integer unrepliedMailCount;
    private List<MailResponse> mailList;
    private String createdAt;
}
