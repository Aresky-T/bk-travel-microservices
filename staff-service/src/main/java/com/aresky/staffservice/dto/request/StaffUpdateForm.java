package com.aresky.staffservice.dto.request;

import java.math.BigDecimal;
import java.util.Date;

import com.aresky.staffservice.model.EGender;

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
    private Date dateOfBirth;
    private BigDecimal basicSalary;
    private Date startedDate;
    private String contractUrl;
    private Integer statusId;
}
