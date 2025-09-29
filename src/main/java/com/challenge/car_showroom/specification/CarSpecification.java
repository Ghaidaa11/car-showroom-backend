package com.challenge.car_showroom.specification;

import com.challenge.car_showroom.dtos.CarFilterRequest;
import com.challenge.car_showroom.models.Car;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class CarSpecification {

    public static Specification<Car> filter(CarFilterRequest filter) {
        return ((root, query, cb) -> {


            root.join("showroom", JoinType.LEFT);

            List<Predicate> predicates = new ArrayList<>();

            if (filter.getVin() != null && !filter.getVin().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("vin")), "%" + filter.getVin().toLowerCase() + "%"));
            }

            if (filter.getMaker() != null && !filter.getMaker().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("maker")), "%" + filter.getMaker().toLowerCase() + "%"));
            }

            if (filter.getModel() != null && !filter.getModel().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("model")), "%" + filter.getModel().toLowerCase() + "%"));
            }

            if (filter.getModelYear() != null) {
                predicates.add(cb.equal(root.get("modelYear"), filter.getModelYear()));
            }

            if (filter.getCarShowroomName() != null && !filter.getCarShowroomName().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("showroom").get("name")), "%" + filter.getCarShowroomName().toLowerCase() + "%"));
            }

            predicates.add(cb.isFalse(root.get("showroom").get("isDeleted")));

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
