package com.aresky.staffservice.dto.response;

import com.aresky.staffservice.model.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatusResponse {
    private Integer id;
    private String name;
    private String description;

    public StatusResponse(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static StatusResponse toDTO(Status status) {
        return new StatusResponse(
                status.getId(),
                status.getName(),
                status.getDescription());
    }
}
