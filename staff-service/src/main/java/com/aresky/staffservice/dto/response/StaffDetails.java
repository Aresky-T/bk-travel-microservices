package com.aresky.staffservice.dto.response;

import java.math.BigDecimal;

import com.aresky.staffservice.model.Department;
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

        public static StaffDetails toDTO(Staff staff) {
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
                                .dateOfBirth(staff.getDateOfBirth().toString())
                                .status(staff.getStatus().name())
                                .startedDate(staff.getStartedDate().toString())
                                .basicSalary(staff.getBasicSalary())
                                .contractUrl(staff.getContractUrl())
                                .accountInfo(
                                                staff.getAccountId() != null ? "Đã có tài khoản trong hệ thống"
                                                                : "Không có tài khoản trong hệ thống")
                                .build();
        }

        public static StaffDetails toDTO(Staff staff, Department department) {
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
                                .dateOfBirth(staff.getDateOfBirth().toString())
                                .status(staff.getStatus().name())
                                .department(department.getName())
                                .startedDate(staff.getStartedDate().toString())
                                .basicSalary(staff.getBasicSalary())
                                .contractUrl(staff.getContractUrl())
                                .accountInfo(
                                                staff.getAccountId() != null ? "Đã có tài khoản trong hệ thống"
                                                                : "Không có tài khoản trong hệ thống")
                                .build();
        }

        public static StaffDetails toDTO(Staff staff, Department department, Position position) {

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
                                .dateOfBirth(staff.getDateOfBirth().toString())
                                .status(staff.getStatus().name())
                                .department(department.getName())
                                .position(position.getName())
                                .startedDate(staff.getStartedDate().toString())
                                .basicSalary(staff.getBasicSalary())
                                .contractUrl(staff.getContractUrl())
                                .accountInfo(
                                                staff.getAccountId() != null ? "Đã có tài khoản trong hệ thống"
                                                                : "Không có tài khoản trong hệ thống")
                                .build();
        }
}
