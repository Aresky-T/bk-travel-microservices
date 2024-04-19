package com.aresky.touristattractionservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchRequest {
    private Integer size;
    private Integer page;
    private String search;
}
