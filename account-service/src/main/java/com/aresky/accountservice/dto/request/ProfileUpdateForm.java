package com.aresky.accountservice.dto.request;

import java.util.Date;

import com.aresky.accountservice.model.EGender;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileUpdateForm {
    private String fullName;
    private String address;
    private String phone;
    private Date dateOfBirth;
    private EGender gender;
}
