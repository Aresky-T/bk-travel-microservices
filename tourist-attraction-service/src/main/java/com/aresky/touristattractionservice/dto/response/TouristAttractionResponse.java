package com.aresky.touristattractionservice.dto.response;

import com.aresky.touristattractionservice.entity.TouristAttraction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TouristAttractionResponse {
    private Integer id;
    private String name;
    private String imageUrl;

    public static TouristAttractionResponse toDto(TouristAttraction entity) {
        return new TouristAttractionResponse(entity.getId(), entity.getName(), entity.getImageUrl());
    }

    public TouristAttractionResponse(Integer id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
