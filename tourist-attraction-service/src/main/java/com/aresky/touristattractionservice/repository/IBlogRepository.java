package com.aresky.touristattractionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aresky.touristattractionservice.entity.Blog;
import java.util.Optional;

@Repository
public interface IBlogRepository extends JpaRepository<Blog, Integer> {
    boolean existsByTitle(String title);

    Optional<Blog> findByTitle(String title);
}
