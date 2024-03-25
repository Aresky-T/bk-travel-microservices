package com.aresky.accountservice.dto.response;

import com.aresky.accountservice.model.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private Integer id;
    private String username;
    private String email;
    private String role;
    private String status;
    private String createdTime;

    public static AccountResponse toDTO(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .username(account.getUsername())
                .email(account.getEmail())
                .role(account.getRole().name())
                .status(account.getActivationStatus().name())
                .createdTime(account.getCreatedTime().toString())
                .build();
    }
}
