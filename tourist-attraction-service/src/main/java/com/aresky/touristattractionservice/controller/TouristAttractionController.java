package com.aresky.touristattractionservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aresky.touristattractionservice.dto.request.BlogCreateForm;
import com.aresky.touristattractionservice.dto.request.BlogItemCreateForm;
import com.aresky.touristattractionservice.dto.request.BlogItemUpdateForm;
import com.aresky.touristattractionservice.dto.request.BlogUpdateForm;
import com.aresky.touristattractionservice.dto.request.FieldRequest;
import com.aresky.touristattractionservice.dto.request.SearchRequest;
import com.aresky.touristattractionservice.dto.request.TouristAttractionCreateForm;
import com.aresky.touristattractionservice.dto.request.TouristAttractionUpdateForm;
import com.aresky.touristattractionservice.service.blog.IBlogService;
import com.aresky.touristattractionservice.service.touristattraction.ITouristAttractionService;

@RestController
@RequestMapping("/api/v1/tourist-attraction")
public class TouristAttractionController {

    @Autowired
    private ITouristAttractionService touristAttractionService;

    @Autowired
    private IBlogService blogService;

    // GET - getAllTouristAttractions()
    @GetMapping
    public ResponseEntity<?> getAllTouristAttractions(Pageable pageable) {
        return ResponseEntity.ok(touristAttractionService.findAll(pageable));
    }

    // GET - getAllTouristAttractions()
    @GetMapping("/search")
    public ResponseEntity<?> getAllTouristAttractions(SearchRequest searchRequest) {
        return ResponseEntity.ok(touristAttractionService.findAll(searchRequest));
    }

    // GET getAllBlogs()
    @GetMapping("/blogs")
    public ResponseEntity<?> getAllBlogs(Pageable pageable) {
        return ResponseEntity.ok(blogService.findAllBlogResponses(pageable));
    }

    // GET - getTouristAttractionDetails(Integer id)
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getTouristAttractionDetails(@PathVariable Integer id) {
        return ResponseEntity.ok(touristAttractionService.findById(id));
    }

    // GET - getTouristAttractionDetails(String name)
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getTouristAttractionDetails(@PathVariable String name) {
        return ResponseEntity.ok(touristAttractionService.findByName(name));
    }

    // GET - getBlogDetails(@RequestParam Integer id)
    @GetMapping("/blog/id/{id}")
    public ResponseEntity<?> getBlogDetails(@PathVariable Integer id) {
        return ResponseEntity.ok(blogService.findBlogDetailsById(id));
    }

    // POST - createTouristAttraction(TouristAttractionCreateForm form)
    @PostMapping
    public ResponseEntity<?> createTouristAttraction(@RequestBody TouristAttractionCreateForm form) {
        touristAttractionService.create(form);
        return ResponseEntity.ok("success");
    }

    // POST - createBlog(BlogCreateForm form)
    @PostMapping("/blog")
    public ResponseEntity<?> createBlog(@RequestBody BlogCreateForm form) {
        blogService.create(form);
        return ResponseEntity.ok("success");
    }

    // POST - addNewBlogItem(Integer blogId, BlogItemCreateForm form)
    @PostMapping("/blog/item")
    public ResponseEntity<?> addNewBlogItem(Integer blogId, BlogItemCreateForm form) {
        return ResponseEntity.ok(blogService.createItem(blogId, form));
    }

    // PUT - updateTouristAttraction(TouristAttractionUpdateForm form)
    @PutMapping
    public ResponseEntity<?> updateTouristAttraction(@RequestParam Integer id,
            @RequestBody TouristAttractionUpdateForm form) {
        return ResponseEntity.ok(touristAttractionService.update(id, form));
    }

    // PUT - updateBlog(Integer blogId, BlogUpdateForm form)
    @PutMapping("/blog")
    public ResponseEntity<?> updateBlog(@RequestParam Integer blogId, @RequestBody BlogUpdateForm form) {
        return ResponseEntity.ok(blogService.updateBlog(blogId, form));
    }

    // PUT - updateBlogItem(Integer blogId, BlogItemUpdateForm form)
    @PutMapping("/blog/item")
    public ResponseEntity<?> updateBlogItem(@RequestParam Integer blogItemId, @RequestBody BlogItemUpdateForm form) {
        return ResponseEntity.ok(blogService.updateBlogItem(blogItemId, form));
    }

    // PATCH- updateTouristAttraction(BlogCreateForm form)
    @PatchMapping
    public ResponseEntity<?> updateTouristAttraction(@RequestParam Integer id, @RequestBody FieldRequest field) {
        return ResponseEntity.ok(touristAttractionService.update(id, field));
    }

    // PATCH - updateBlog(Integer blogId, FieldRequest field)
    @PatchMapping("/blog/field")
    public ResponseEntity<?> updateBlog(@RequestParam Integer blogId, @RequestBody FieldRequest field) {
        return ResponseEntity.ok(blogService.updateBlog(blogId, field));
    }

    // PATCH - updateBlog(Integer blogId, Map<String, Object> fields)
    @PatchMapping("/blog")
    public ResponseEntity<?> updateBlog(@RequestParam Integer blogId, @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(blogService.updateBlog(blogId, fields));
    }

    // PATCH - updateBlogItem(Integer blogItemId, Map<String, Object> fields)
    @PatchMapping("/blog/item")
    public ResponseEntity<?> updateBlogItem(@RequestParam Integer blogItemId, @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(blogService.updateBlogItem(blogItemId, fields));
    }

    // DELETE - deleteTouristAttraction(Integer id)
    @DeleteMapping
    public ResponseEntity<?> deleteTouristAttraction(@RequestParam Integer id) {
        touristAttractionService.delete(id);
        return ResponseEntity.ok("success");
    }

    // DELETE - deleteBlog(Integer id)
    @DeleteMapping("/blog")
    public ResponseEntity<?> deleteBlog(@RequestParam Integer blogId) {
        blogService.deleteBlog(blogId);
        return ResponseEntity.ok("success");
    }

    // DELETE - deleteBlogItem(Integer id)
    @DeleteMapping("/blog/item")
    public ResponseEntity<?> deleteBlogItem(@RequestParam Integer blogItemId) {
        blogService.deleteBlogItem(blogItemId);
        return ResponseEntity.ok("success");
    }
}
