package com.aresky.touristattractionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aresky.touristattractionservice.entity.BlogItem;

@Repository
public interface IBlogItemRepository extends JpaRepository<BlogItem, Integer> {
    List<BlogItem> findAllByBlogId(Integer blogId);
}
