package com.aresky.staffservice.dto.response;

import java.time.format.DateTimeFormatter;

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
    private Integer positionId;
    private Integer statusId;

    public static StaffResponse toDTO(Staff staff) {
        return StaffResponse.builder()
                .id(staff.getId())
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .email(staff.getEmail())
                .phone(staff.getPhone())
                .gender(staff.getGender().name())
                .dateOfBirth(staff.getDateOfBirth().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .positionId(staff.getPositionId())
                .statusId(staff.getStatusId())
                .build();
    }
}
