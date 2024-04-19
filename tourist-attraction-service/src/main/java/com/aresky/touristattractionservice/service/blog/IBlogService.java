package com.aresky.touristattractionservice.service.blog;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aresky.touristattractionservice.dto.request.BlogCreateForm;
import com.aresky.touristattractionservice.dto.request.BlogItemCreateForm;
import com.aresky.touristattractionservice.dto.request.BlogItemUpdateForm;
import com.aresky.touristattractionservice.dto.request.BlogUpdateForm;
import com.aresky.touristattractionservice.dto.request.FieldRequest;
import com.aresky.touristattractionservice.dto.response.BlogDetails;
import com.aresky.touristattractionservice.dto.response.BlogResponse;
import com.aresky.touristattractionservice.dto.response.BlogUpdatedResponse;
import com.aresky.touristattractionservice.dto.response.FieldResponse;
import com.aresky.touristattractionservice.dto.response.BlogDetails.Item;

public interface IBlogService {
    void create(BlogCreateForm form);

    Item createItem(Integer blogId, BlogItemCreateForm form);

    Page<BlogResponse> findAllBlogResponses(Pageable pageable);

    List<Item> findAllBlogItems(Integer blogId);

    BlogDetails findBlogDetailsById(Integer id);

    BlogUpdatedResponse updateBlog(Integer blogId, BlogUpdateForm form);

    Map<String, Object> updateBlog(Integer blogId, Map<String, Object> fields);

    FieldResponse updateBlog(Integer blogId, FieldRequest field);

    Item updateBlogItem(Integer blogItemId, BlogItemUpdateForm form);

    Map<String, Object> updateBlogItem(Integer blogItemId, Map<String, Object> fields);

    void deleteBlog(Integer id);

    void deleteBlogItem(Integer blogItemId);
}
