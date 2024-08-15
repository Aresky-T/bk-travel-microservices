package com.aresky.mailservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailBoxResponse {
    private Integer id;
    private CustomerResponse customer;
    private Integer totalMail;
    private Integer unrepliedMailCount;
    private String createdAt;
    private String updatedAt;
}
