package com.aresky.touristattractionservice.dto.request;

import com.aresky.touristattractionservice.entity.BlogItem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogItemCreateForm {
    private String subTitle;
    private String imageUrl;
    private String content;

    public static BlogItem toBlogItem(BlogItemCreateForm dto) {
        return new BlogItem(dto.subTitle, dto.imageUrl, dto.content);
    }
}
