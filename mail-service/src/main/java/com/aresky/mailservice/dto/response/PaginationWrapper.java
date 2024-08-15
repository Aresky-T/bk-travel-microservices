package com.aresky.mailservice.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PaginationWrapper <T> {
    private Boolean first;
    private Boolean last;
    private Boolean empty;
    private Integer page;
    private Integer size;
    private Integer numberOfElements;
    private Long totalElements;
    private Integer totalPages;
    private final List<T> content = new ArrayList<>();

    public PaginationWrapper(List<T> content, Long totalElements, Integer page, Integer size){
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.content.addAll(content);
        this.numberOfElements = content.size();
        this.empty = content.isEmpty();
        this.totalPages = (int) Math.ceil((double) totalElements / (double) size);
        this.first = isFirstPage();
        this.last = isLastPage();
    }

    public boolean hasNextPage(){
        return page + 1 < totalPages;
    }

    public boolean hasPreviousPage(){
        return page > 0;
    }

    public boolean isLastPage(){
        return !hasNextPage();
    }

    public boolean isFirstPage(){
        return !hasPreviousPage();
    }
}
