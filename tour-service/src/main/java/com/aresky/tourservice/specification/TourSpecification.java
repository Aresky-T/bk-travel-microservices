package com.aresky.tourservice.specification;

import org.springframework.data.jpa.domain.Specification;

import com.aresky.tourservice.dto.request.TourFilter;
import com.aresky.tourservice.entity.Tour;

import jakarta.persistence.criteria.Predicate;

public class TourSpecification {

    public static Specification<Tour> buildWhere(TourFilter filter) {
        return (root, query, criteriaBuilder) -> {

            // Tạo một Predicate để chứa tất cả các điều kiện filter
            Predicate predicate = criteriaBuilder.conjunction();

            String departureLocation = filter.getDepartureLocation();

            if (departureLocation != null && !departureLocation.isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("departureLocation"), filter.getDepartureLocation()));
            }

            // Thêm điều kiện filter cho destination nếu nó được cung cấp
            String destination = filter.getDestination();
            if (destination != null && !destination.isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("destinations"), "%" + destination + "%"));
            }

            // Thêm điều kiện filter cho vehicle nếu nó được cung cấp
            String vehicle = filter.getVehicle();
            if (vehicle != null && !vehicle.isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("vehicle"), vehicle));
            }

            // Thêm điều kiện filter cho minPrice nếu nó được cung cấp
            Integer minPrice = filter.getMinPrice();
            if (minPrice != null && minPrice > 0) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.greaterThanOrEqualTo(root.get("adultPrice"), minPrice));
            }

            // Thêm điều kiện filter cho maxPrice nếu nó được cung cấp
            Integer maxPrice = filter.getMaxPrice();
            if (maxPrice != null && maxPrice > 0) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.lessThanOrEqualTo(root.get("adultPrice"), maxPrice));
            }

            // Trả về Predicate kết hợp tất cả các điều kiện filter
            return predicate;
        };
    }
}
