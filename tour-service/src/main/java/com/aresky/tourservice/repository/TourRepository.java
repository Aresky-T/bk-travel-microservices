package com.aresky.tourservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.aresky.tourservice.entity.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer>, JpaSpecificationExecutor<Tour> {

    List<Tour> findAllBy(Pageable pageable);

    Tour findByTitle(String title);

    Boolean existsByTitle(String title);
}
