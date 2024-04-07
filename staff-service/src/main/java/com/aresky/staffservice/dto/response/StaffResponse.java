package com.aresky.staffservice.dto.response;

import com.aresky.staffservice.model.Position;
import com.aresky.staffservice.model.Staff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String gender;
    private String dateOfBirth;
    private String position;
    private String status;

    public static StaffResponse toDTO(Staff staff) {
        return StaffResponse.builder()
                .id(staff.getId())
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .email(staff.getEmail())
                .phone(staff.getPhone())
                .gender(staff.getGender().name())
                .dateOfBirth(staff.getDateOfBirth().toString())
                .status(staff.getStatus().name())
                .build();
    }

    public static StaffResponse toDTO(Staff staff, Position position) {
        return StaffResponse.builder()
                .id(staff.getId())
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .email(staff.getEmail())
                .phone(staff.getPhone())
                .gender(staff.getGender().name())
                .dateOfBirth(staff.getDateOfBirth().toString())
                .position(position.getName())
                .status(staff.getStatus().name())
                .build();
    }
}
