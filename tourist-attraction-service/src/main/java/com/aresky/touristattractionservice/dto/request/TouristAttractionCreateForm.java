package com.aresky.touristattractionservice.dto.request;

import com.aresky.touristattractionservice.entity.TouristAttraction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TouristAttractionCreateForm {
    private String name;
    private String imageUrl;

    public TouristAttraction toEntity() {
        return new TouristAttraction(this.name, this.imageUrl);
    }
}
