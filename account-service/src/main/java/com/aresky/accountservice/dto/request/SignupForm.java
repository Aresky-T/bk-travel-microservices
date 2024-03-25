package com.aresky.accountservice.dto.request;

import com.aresky.accountservice.model.Account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupForm {
    private String username;
    private String email;
    private String password;

    public Account toEntity() {
        return new Account(username, email, password);
    }
}
