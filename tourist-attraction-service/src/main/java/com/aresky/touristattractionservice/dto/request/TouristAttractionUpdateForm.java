package com.aresky.touristattractionservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TouristAttractionUpdateForm {
    private String name;
    private String imageUrl;
}
