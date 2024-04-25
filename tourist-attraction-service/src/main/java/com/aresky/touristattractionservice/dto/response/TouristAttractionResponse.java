package com.aresky.touristattractionservice.dto.response;

import com.aresky.touristattractionservice.entity.TouristAttraction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TouristAttractionResponse {
    private Integer id;
    private String name;
    private String introduction;
    private String imageUrl;

    public static TouristAttractionResponse toDto(TouristAttraction entity) {
        return new TouristAttractionResponse(entity);
    }

    public TouristAttractionResponse(Integer id, String name, String imageUrl, String introduction) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.introduction = introduction;
    }

    public TouristAttractionResponse(TouristAttraction entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.imageUrl = entity.getImageUrl();
        this.introduction = entity.getIntroduction();
    }
}
