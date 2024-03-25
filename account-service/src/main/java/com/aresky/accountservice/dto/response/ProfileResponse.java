package com.aresky.accountservice.dto.response;

import com.aresky.accountservice.model.Account;
import com.aresky.accountservice.model.Profile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileResponse {
    private Integer id;
    private String avatarUrl;
    private String fullName;
    private String address;
    private String phone;
    private String dateOfBirth;
    private String gender;
    private AccountDTO account;

    public static ProfileResponse toDTO(Profile profile, Account account) {
        ProfileResponse dto = new ProfileResponse();
        dto.setId(profile.getId());
        dto.setAvatarUrl(profile.getAvatarUrl());
        dto.setFullName(profile.getFullName());
        dto.setAddress(profile.getAddress());
        dto.setPhone(profile.getPhone());
        dto.setDateOfBirth(profile.getDateOfBirth() != null ? profile.getDateOfBirth().toString() : null);
        dto.setGender(profile.getGender() != null ? profile.getGender().name() : null);
        dto.setAccount(new AccountDTO(account.getUsername(), account.getEmail()));
        return dto;
    }

    @Data
    @NoArgsConstructor
    public static class AccountDTO {
        private String username;
        private String email;

        public AccountDTO(String username, String email) {
            this.username = username;
            this.email = email;
        }
    }
}
