package com.aresky.touristattractionservice.dto.response;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.aresky.touristattractionservice.entity.Blog;
import com.aresky.touristattractionservice.entity.TouristAttraction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TouristAttractionDetails {
    private Integer id;
    private String name;
    private String introduction;
    private String imageUrl;
    private List<BlogDTO> blogs;

    public static TouristAttractionDetails toDto(TouristAttraction entity) {
        return new TouristAttractionDetails(entity);
    }

    public TouristAttractionDetails(Integer id, String name, String imageUrl, String introduction) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.introduction = introduction;
    }

    public TouristAttractionDetails(TouristAttraction entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.imageUrl = entity.getImageUrl();
        this.introduction = entity.getIntroduction();
        this.blogs = entity.getBlogs().stream().map(BlogDTO::toDto).toList();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BlogDTO {
        private Integer id;
        private String title;
        private String imageUrl;
        private String intro;
        private String author;
        private String createdTime;

        public static BlogDTO toDto(Blog blog) {
            return BlogDTO.builder()
                    .id(blog.getId())
                    .title(blog.getTitle())
                    .imageUrl(blog.getImageUrl())
                    .intro(blog.getIntro())
                    .author(blog.getAuthor())
                    .createdTime(DateTimeFormatter.ISO_INSTANT.format(blog.getCreatedTime().toInstant()))
                    .build();
        }
    }
}
