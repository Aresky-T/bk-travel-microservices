package com.aresky.staffservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.aresky.staffservice.model.Position;
import com.aresky.staffservice.model.Staff;

@Data
@NoArgsConstructor
public class PositionDetails {
    private Integer id;
    private Integer departmentId;
    private String name;
    private String description;
    private Integer headcount;
    private List<StaffResponse> staffs = new ArrayList<>();

    public PositionDetails(Integer id, Integer departmentId, String name, String description, Integer headcount) {
        this.id = id;
        this.departmentId = departmentId;
        this.name = name;
        this.description = description;
        this.headcount = headcount;
    }

    public static PositionDetails toDTO(Position position) {
        PositionDetails dto = new PositionDetails(
                position.getId(),
                position.getDepartmentId(),
                position.getName(),
                position.getDescription(),
                position.getHeadcount());
        return dto;
    }

    public static PositionDetails toDTO(Position position, List<Staff> staffs) {
        PositionDetails dto = new PositionDetails(
                position.getId(),
                position.getDepartmentId(),
                position.getName(),
                position.getDescription(),
                position.getHeadcount());

        if (!staffs.isEmpty()) {
            dto.staffs.addAll(
                    staffs.stream()
                            .map(StaffResponse::toDTO)
                            .collect(Collectors.toList()));
        }

        return dto;
    }
}
