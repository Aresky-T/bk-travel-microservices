package com.aresky.touristattractionservice.specification;

import com.aresky.touristattractionservice.entity.TouristAttraction;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class TouristAttractionSpec {
    public static Specification<TouristAttraction> buildWhere(String search){
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if(search != null && !search.isEmpty()){
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("name"), "%" + search + "%"));
            }

            return predicate;
        };
    }
}
