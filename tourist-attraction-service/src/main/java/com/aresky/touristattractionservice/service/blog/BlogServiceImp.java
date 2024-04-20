package com.aresky.touristattractionservice.service.blog;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aresky.touristattractionservice.dto.request.BlogCreateForm;
import com.aresky.touristattractionservice.dto.request.BlogItemCreateForm;
import com.aresky.touristattractionservice.dto.request.BlogItemUpdateForm;
import com.aresky.touristattractionservice.dto.request.BlogUpdateForm;
import com.aresky.touristattractionservice.dto.request.FieldRequest;

import com.aresky.touristattractionservice.dto.response.BlogDetails;
import com.aresky.touristattractionservice.dto.response.BlogDetails.Item;
import com.aresky.touristattractionservice.dto.response.BlogResponse;
import com.aresky.touristattractionservice.dto.response.BlogUpdatedResponse;
import com.aresky.touristattractionservice.dto.response.FieldResponse;

import com.aresky.touristattractionservice.entity.Blog;
import com.aresky.touristattractionservice.entity.BlogItem;
import com.aresky.touristattractionservice.entity.TouristAttraction;

import com.aresky.touristattractionservice.exception.TouristAttractionException;

import com.aresky.touristattractionservice.repository.IBlogItemRepository;
import com.aresky.touristattractionservice.repository.IBlogRepository;
import com.aresky.touristattractionservice.repository.ITouristAttractionRepository;

import com.aresky.touristattractionservice.utils.FieldUtils;

import jakarta.transaction.Transactional;

@Service
public class BlogServiceImp implements IBlogService {

    @Autowired
    private IBlogRepository blogRepository;

    @Autowired
    private IBlogItemRepository blogItemRepository;

    @Autowired
    private ITouristAttractionRepository touristAttractionRepository;

    private static String[] BLACK_LIST = {
            "id", "blogId", "touristAttractionId", "blog", "touristAttraction"
    };

    @Transactional
    @Override
    public void create(BlogCreateForm form) {
        String title = form.getTitle().trim();
        Integer touristAttractionId = form.getTouristAttractionId();

        Optional<TouristAttraction> taOptional = touristAttractionRepository.findById(touristAttractionId);

        if (taOptional.isEmpty()) {
            throw new TouristAttractionException("Invalid touristAttractionId");
        }

        checkNotExistBlog(title);

        Blog blog = BlogCreateForm.toBlog(form);
        blog.setTouristAttraction(taOptional.get());

        Blog newBlog = blogRepository.save(blog);

        Set<BlogItem> blogItems = BlogCreateForm.getBlogItems(form);

        if (blogItems.size() == 0) {
            throw new TouristAttractionException("Các mục con của bài viết không được để trống");
        }

        blogItems.forEach(item -> {
            item.setBlog(newBlog);
        });

        blogItemRepository.saveAll(blogItems);
    }

    @Transactional
    @Override
    public Item createItem(Integer blogId, BlogItemCreateForm form) {
        Blog blog = findBlogEntity(blogId);
        BlogItem newBlogItem = BlogItemCreateForm.toBlogItem(form);
        newBlogItem.setBlog(blog);
        return Item.toDto(blogItemRepository.save(newBlogItem));
    }

    @Override
    public Page<BlogResponse> findAllBlogResponses(Pageable pageable) {
        Page<Blog> blogs = blogRepository.findAll(pageable);
        return blogs.map(BlogResponse::toDto);
    }

    @Override
    public List<Item> findAllBlogItems(Integer blogId) {
        checkExistBlog(blogId);
        List<BlogItem> blogItems = blogItemRepository.findAllByBlogId(blogId);
        return blogItems.stream().map(Item::toDto).toList();
    }

    @Override
    public BlogDetails findBlogDetailsById(Integer blogId) {
        Blog blog = findBlogEntity(blogId);
        return BlogDetails.toDto(blog);
    }

    @Transactional
    @Override
    public BlogUpdatedResponse updateBlog(Integer blogId, BlogUpdateForm form) {
        Blog blog = findBlogEntity(blogId);
        Field[] formFields = FieldUtils.findFields(form);
        FieldUtils.checkFieldsNotNullOrNotEmpty(formFields);

        for (Field dtoField : formFields) {
            dtoField.setAccessible(true);
            String fieldName = dtoField.getName();
            Object newValue = FieldUtils.findFieldValue(form, dtoField);

            // check field in Blog
            Field blogField = FieldUtils.findField(blog, fieldName);

            // check new value is not null or not empty if value type equals String
            if (blogField.getType().equals(String.class)) {
                FieldUtils.checkValueOfStringField(fieldName, (String) newValue);
            }

            FieldUtils.setFieldValue(blog, blogField, newValue);
        }

        Blog updatedBlog = blogRepository.save(blog);
        return BlogUpdatedResponse.toDto(updatedBlog);
    }

    @Transactional
    @Override
    public Map<String, Object> updateBlog(Integer blogId, Map<String, Object> fields) {
        Blog blog = findBlogEntity(blogId);
        Map<String, Object> resultMap = new HashMap<>();

        for (Entry<String, Object> entry : fields.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            Field blogField = FieldUtils.findField(blog, key);
            checkBlackListKey(blog, key);

            if (blogField.getType().equals(String.class)) {
                FieldUtils.checkValueOfStringField(blogField, (String) value);
            }

            FieldUtils.setFieldValue(blog, blogField, value);
            resultMap.put(key, value);
        }

        blogRepository.save(blog);
        return resultMap;
    }

    @Transactional
    @Override
    public FieldResponse updateBlog(Integer blogId, FieldRequest field) {
        Blog blog = findBlogEntity(blogId);
        String key = field.getKey();
        Object value = field.getValue();

        Field blogField = FieldUtils.findField(blog, key);
        checkBlackListKey(blog, key);

        if (blogField.getType().equals(String.class)) {
            FieldUtils.checkValueOfStringField(blogField, (String) value);
        }

        FieldUtils.setFieldValue(blog, blogField, value);
        blogRepository.save(blog);
        return new FieldResponse(key, value);
    }

    @Transactional
    @Override
    public Item updateBlogItem(Integer blogItemId, BlogItemUpdateForm form) {
        BlogItem blogItem = findBlogItemEntity(blogItemId);
        Field[] formFields = FieldUtils.findFields(form);
        FieldUtils.checkFieldsNotNullOrNotEmpty(formFields);

        for (Field dtoField : formFields) {
            dtoField.setAccessible(true);
            String fieldName = dtoField.getName();
            Object newValue = FieldUtils.findFieldValue(form, dtoField);

            // check field in BlogItem
            Field blogItemField = FieldUtils.findField(blogItem, fieldName);

            // check new value is not null or not empty if value type equals String
            if (blogItemField.getType().equals(String.class)) {
                FieldUtils.checkValueOfStringField(fieldName, (String) newValue);
            }

            FieldUtils.setFieldValue(blogItem, blogItemField, newValue);
        }

        BlogItem updatedBlogItem = blogItemRepository.save(blogItem);
        return Item.toDto(updatedBlogItem);
    }

    @Transactional
    @Override
    public Map<String, Object> updateBlogItem(Integer blogItemId, Map<String, Object> fields) {
        BlogItem blogItem = findBlogItemEntity(blogItemId);
        Map<String, Object> resultMap = new HashMap<>();

        for (Entry<String, Object> entry : fields.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            Field field = FieldUtils.findField(blogItem, key);
            checkBlackListKey(blogItem, key);

            if (field.getType().equals(String.class)) {
                FieldUtils.checkValueOfStringField(field, (String) value);
            }

            FieldUtils.setFieldValue(blogItem, field, value);
            resultMap.put(key, value);
        }

        blogItemRepository.save(blogItem);
        return resultMap;
    }

    @Transactional
    @Override
    public void deleteBlog(Integer id) {
        checkExistBlog(id);
        blogRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteBlogItem(Integer blogItemId) {
        checkExistBlogItem(blogItemId);
        blogItemRepository.deleteById(blogItemId);
    }

    public void checkExistBlog(Integer blogId) {
        if (!blogRepository.existsById(blogId)) {
            throw new TouristAttractionException("Invalid id!");
        }
    }

    public void checkExistBlog(String blogTitle) {
        if (!blogRepository.existsByTitle(blogTitle)) {
            throw new TouristAttractionException("Tiêu đề không tồn tại!");
        }
    }

    public void checkExistBlogItem(Integer blogItemId) {
        if (!blogItemRepository.existsById(blogItemId)) {
            throw new TouristAttractionException("Invalid blogItemId!");
        }
    }

    public void checkNotExistBlog(String blogTitle) {
        if (blogRepository.existsByTitle(blogTitle)) {
            throw new TouristAttractionException("Tiêu đề đã tồn tại!");
        }
    }

    private Blog findBlogEntity(Integer blogId) {
        checkExistBlog(blogId);
        return blogRepository.findById(blogId).get();
    }

    private BlogItem findBlogItemEntity(Integer blogItemId) {
        checkExistBlogItem(blogItemId);
        return blogItemRepository.findById(blogItemId).get();
    }

    private void checkBlackListKey(Object obj, String fieldName) {
        if (isBlackListKey(fieldName)) {
            throw new TouristAttractionException(
                    "Cannot update " + fieldName + " for " + obj.getClass().getSimpleName());
        }
    }

    private boolean isBlackListKey(String key) {
        for (String item : BLACK_LIST) {
            if (item.equals(key.trim())) {
                return true;
            }
        }
        return false;
    }
}
