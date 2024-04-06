package com.aresky.staffservice.dto.response;

import com.aresky.staffservice.model.Position;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PositionResponse {
    private Integer id;
    private Integer departmentId;
    private String name;
    private String description;
    private Integer headcount;

    public PositionResponse(Position position) {
        this.id = position.getId();
        this.departmentId = position.getDepartmentId();
        this.name = position.getName();
        this.description = position.getDescription();
        this.headcount = position.getHeadcount();
    }

    public static PositionResponse toDTO(Position position) {
        return new PositionResponse(position);
    }
}
