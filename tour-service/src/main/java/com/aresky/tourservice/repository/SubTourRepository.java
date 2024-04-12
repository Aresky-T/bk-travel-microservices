package com.aresky.tourservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aresky.tourservice.entity.SubTour;

@Repository
public interface SubTourRepository extends JpaRepository<SubTour, Integer>, JpaSpecificationExecutor<SubTour> {
    Optional<SubTour> findByTourCode(String tourCode);

    Optional<SubTour> findByTitle(String title);

    List<SubTour> findAllBy(Pageable pageable);

    List<SubTour> findAllByTourId(Integer tourId);

    List<SubTour> findAllByTitleLike(String title);

    @Query("SELECT s FROM SubTour AS s WHERE s.status = 'NOT_STARTED' ORDER BY s.createdTime DESC LIMIT :count")
    List<SubTour> findAllOrderByCreatedTime(@Param("count") int count);

    Boolean existsByTitle(String title);

    Boolean existsByTourId(Integer tourId);

    Boolean existsByTourCode(String tourCode);

    void deleteAllByTourId(Integer tourId);

    void deleteByTourCode(String tourCode);
}
