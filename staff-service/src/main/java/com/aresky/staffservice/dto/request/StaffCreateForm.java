package com.aresky.staffservice.dto.request;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;

import com.aresky.staffservice.model.EGender;
import com.aresky.staffservice.model.Staff;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StaffCreateForm {
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

    public static Staff toStaff(StaffCreateForm dto) {
        return Staff.builder()
                .firstName(dto.firstName)
                .lastName(dto.lastName)
                .avatarUrl(dto.avatarUrl)
                .gender(dto.gender)
                .dateOfBirth(dto.dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .email(dto.email)
                .phone(dto.phone)
                .address(dto.address)
                .description(dto.description)
                .basicSalary(dto.basicSalary)
                .startedDate(dto.startedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .contractUrl(dto.contractUrl)
                .build();
    }
}
