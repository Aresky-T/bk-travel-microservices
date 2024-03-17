package com.aresky.authservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePasswordForm {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
