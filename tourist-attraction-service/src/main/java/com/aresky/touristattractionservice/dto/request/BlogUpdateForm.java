package com.aresky.touristattractionservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogUpdateForm {
    private String title;
    private String imageUrl;
    private String intro;
    private String author;
}
