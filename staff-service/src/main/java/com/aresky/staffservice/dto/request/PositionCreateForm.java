package com.aresky.staffservice.dto.request;

import com.aresky.staffservice.model.Position;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PositionCreateForm {
    private String name;
    private String description;
    private Integer departmentId;
    private Integer headcount;

    public static Position toPosition(PositionCreateForm dto) {
        return new Position(dto.name, dto.description, dto.departmentId, dto.headcount);
    }
}
