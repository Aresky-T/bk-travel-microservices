package com.aresky.touristattractionservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogItemUpdateForm {
    private String subTitle;
    private String imageUrl;
    private String content;
}
