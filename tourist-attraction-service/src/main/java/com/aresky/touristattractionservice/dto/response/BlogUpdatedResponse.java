package com.aresky.touristattractionservice.dto.response;

import com.aresky.touristattractionservice.entity.Blog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogUpdatedResponse {
    private String title;
    private String imageUrl;
    private String intro;
    private String author;

    public static BlogUpdatedResponse toDto(Blog blog) {
        return BlogUpdatedResponse.builder()
                .title(blog.getTitle())
                .imageUrl(blog.getImageUrl())
                .intro(blog.getIntro())
                .author(blog.getAuthor())
                .build();
    }
}
