package com.aresky.touristattractionservice.dto.request;

import java.util.Set;
import java.util.stream.Collectors;

import com.aresky.touristattractionservice.entity.Blog;
import com.aresky.touristattractionservice.entity.BlogItem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogCreateForm {
    private String title;
    private String imageUrl;
    private String intro;
    private String author;
    private Integer touristAttractionId;
    private Set<BlogItemCreateForm> items;

    public static Blog toBlog(BlogCreateForm dto) {
        if (dto.author == null || dto.author.isEmpty()) {
            return new Blog(dto.title, dto.imageUrl, dto.intro);
        }

        return new Blog(dto.title, dto.imageUrl, dto.intro, dto.author);
    }

    public static Set<BlogItem> getBlogItems(BlogCreateForm dto) {
        return dto.items.stream().map(BlogItemCreateForm::toBlogItem).collect(Collectors.toSet());
    }
}
