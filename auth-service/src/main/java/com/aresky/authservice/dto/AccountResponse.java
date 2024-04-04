package com.aresky.authservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountResponse {
    private Integer id;
    private String username;
    private String email;
    private String role;
    private String status;
    private String createdTime;
}
