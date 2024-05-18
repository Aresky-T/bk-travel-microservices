package com.aresky.staffservice.dto.request;

import java.time.LocalDate;

import com.aresky.staffservice.model.EGender;
import com.aresky.staffservice.model.EStaffStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StaffUpdateForm {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String description;
    private String avatarUrl;
    private EGender gender;
    private LocalDate dateOfBirth;
    private LocalDate hireDate;
    private String contractUrl;
    private EStaffStatus status;
}
