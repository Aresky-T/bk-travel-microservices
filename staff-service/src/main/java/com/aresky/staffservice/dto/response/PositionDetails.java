package com.aresky.staffservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import com.aresky.staffservice.model.Position;

@Data
@NoArgsConstructor
public class PositionDetails {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal basicSalary;
    // private List<StaffResponse> staffs = new ArrayList<>();

    public PositionDetails(Integer id, String name, String description, BigDecimal basicSalary) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.basicSalary = basicSalary;
    }

    public static PositionDetails toDTO(Position position) {
        return new PositionDetails(
                position.getId(),
                position.getName(),
                position.getDescription(),
                position.getBasicSalary());
    }

    // public static PositionDetails toDTO(Position position, List<Staff> staffs) {
    // PositionDetails dto = toDTO(position);

    // if (!staffs.isEmpty()) {
    // dto.staffs.addAll(staffs.stream().map(StaffResponse::toDTO).toList());
    // }

    // return dto;
    // }
}
