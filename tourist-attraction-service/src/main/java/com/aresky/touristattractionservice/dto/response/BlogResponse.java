package com.aresky.touristattractionservice.dto.response;

import java.time.format.DateTimeFormatter;

import com.aresky.touristattractionservice.entity.Blog;
import com.aresky.touristattractionservice.entity.TouristAttraction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogResponse {
    private Integer id;
    private Integer touristAttractionId;
    private String touristAttractionName;
    private String title;
    private String imageUrl;
    private String intro;
    private String author;
    private String createdTime;

    public static BlogResponse toDto(Blog blog) {
        TouristAttraction touristAttraction = blog.getTouristAttraction();

        return BlogResponse.builder()
                .id(blog.getId())
                .touristAttractionId(touristAttraction.getId())
                .touristAttractionName(touristAttraction.getName())
                .title(blog.getTitle())
                .imageUrl(blog.getImageUrl())
                .intro(blog.getIntro())
                .author(blog.getAuthor())
                .createdTime(DateTimeFormatter.ISO_INSTANT.format(blog.getCreatedTime().toInstant()))
                .build();
    }

    public static BlogResponse toDto(Blog blog, TouristAttraction touristAttraction) {
        BlogResponse dto = toDto(blog);
        dto.touristAttractionId = touristAttraction.getId();
        dto.touristAttractionName = touristAttraction.getName();

        return dto;
    }
}
