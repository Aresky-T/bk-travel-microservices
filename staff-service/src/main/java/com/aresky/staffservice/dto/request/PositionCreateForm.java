package com.aresky.staffservice.dto.request;

import java.math.BigDecimal;

import com.aresky.staffservice.model.Position;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PositionCreateForm {
    private String name;
    private String description;
    private BigDecimal basicSalary;

    public static Position toPosition(PositionCreateForm dto) {
        return Position.builder()
                .name(dto.name)
                .description(dto.description)
                .basicSalary(dto.basicSalary)
                .build();
    }
}
