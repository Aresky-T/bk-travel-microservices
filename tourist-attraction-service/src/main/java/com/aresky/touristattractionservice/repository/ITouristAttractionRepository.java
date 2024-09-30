package com.aresky.touristattractionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aresky.touristattractionservice.entity.TouristAttraction;
import java.util.Optional;
import java.util.List;

@Repository
public interface ITouristAttractionRepository extends JpaRepository<TouristAttraction, Integer>, JpaSpecificationExecutor<TouristAttraction> {
    Optional<TouristAttraction> findByName(String name);

    List<TouristAttraction> findAllByNameLike(String name);

    @Query(value = "SELECT * FROM tourist_attraction t WHERE t.name LIKE %:search% LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<TouristAttraction> findAllWithSearch(@Param("search") String search, @Param("limit") Integer limit,
            @Param("offset") Integer offset);

    boolean existsByName(String name);
}
