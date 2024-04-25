package com.aresky.touristattractionservice.dto.request;

import java.util.List;

import com.aresky.touristattractionservice.entity.BlogItem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogUpdateForm {
    private String title;
    private String imageUrl;
    private String intro;
    private String author;
    private List<ItemDTO> items;

    @Data
    @NoArgsConstructor
    public static class ItemDTO {
        private Integer id;
        private String subTitle;
        private String imageUrl;
        private String content;

        public static BlogItem toBlogItem(ItemDTO dto) {
            return new BlogItem(dto.id, dto.subTitle, dto.imageUrl, dto.content);
        }
    }
}
