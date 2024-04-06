package com.aresky.staffservice.dto.response;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import com.aresky.staffservice.model.Department;
import com.aresky.staffservice.model.Position;
import com.aresky.staffservice.model.Staff;
import com.aresky.staffservice.model.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffDetails {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String gender;
    private String dateOfBirth;
    private String avatarUrl;
    private String description;
    private String status;
    public String department;
    public String position;
    private String startedDate;
    private BigDecimal basicSalary;
    private String contractUrl;
    private String accountInfo;

    public static StaffDetails toDTO(Staff staff, Department department, Position position, Status status) {

        return StaffDetails.builder()
                .id(staff.getId())
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .avatarUrl(staff.getAvatarUrl())
                .email(staff.getEmail())
                .phone(staff.getPhone())
                .address(staff.getAddress())
                .description(staff.getDescription())
                .gender(staff.getGender().name())
                .dateOfBirth(staff.getDateOfBirth().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .status(status.getName())
                .department(department.getName())
                .position(position.getName())
                .startedDate(staff.getStartedDate().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .basicSalary(staff.getBasicSalary())
                .contractUrl(staff.getContractUrl())
                .accountInfo(
                        staff.getAccountId() != null ? "Đã có tài khoản trong hệ thống"
                                : "Không có tài khoản trong hệ thống")
                .build();
    }
}
