package com.aresky.touristattractionservice.dto.response;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.aresky.touristattractionservice.entity.Blog;
import com.aresky.touristattractionservice.entity.BlogItem;
import com.aresky.touristattractionservice.entity.TouristAttraction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogDetails {
    private Integer id;
    private String title;
    private String imageUrl;
    private String intro;
    private String author;
    private String createdTime;
    private Integer touristAttractionId;
    private String touristAttractionName;
    private List<Item> items;

    public static BlogDetails toDto(Blog blog) {
        TouristAttraction touristAttraction = blog.getTouristAttraction();
        List<Item> blogItems = blog.getItems().stream().map(Item::toDto).toList();

        return BlogDetails.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .imageUrl(blog.getImageUrl())
                .intro(blog.getIntro())
                .author(blog.getAuthor())
                .createdTime(DateTimeFormatter.ISO_INSTANT.format(blog.getCreatedTime().toInstant()))
                .touristAttractionId(touristAttraction.getId())
                .touristAttractionName(touristAttraction.getName())
                .items(blogItems)
                .build();
    }

    @Data
    @NoArgsConstructor
    public static class Item {
        private Integer id;
        private String subTitle;
        private String imageUrl;
        private String content;

        public Item(Integer id, String subTitle, String imageUrl, String content) {
            this.id = id;
            this.subTitle = subTitle;
            this.imageUrl = imageUrl;
            this.content = content;
        }

        public static Item toDto(BlogItem blogItem) {
            return new Item(blogItem.getId(), blogItem.getSubTitle(), blogItem.getImageUrl(), blogItem.getContent());
        }
    }
}
