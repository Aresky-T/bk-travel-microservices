package com.aresky.staffservice.dto.response;

import com.aresky.staffservice.model.Position;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PositionResponse {
    private Integer id;
    private String name;

    public PositionResponse(Position position) {
        this.id = position.getId();
        this.name = position.getName();
    }

    public static PositionResponse toDTO(Position position) {
        return new PositionResponse(position);
    }
}
