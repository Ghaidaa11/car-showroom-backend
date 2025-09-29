package com.challenge.car_showroom.repositories;

import com.challenge.car_showroom.dtos.CarFilterRequest;
import com.challenge.car_showroom.dtos.CarResponseDto;
import com.challenge.car_showroom.models.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CarRepo extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

    boolean existsByVinAndShowroom_IsDeleted(String vin, boolean isDeleted);

//    Page<CarResponseDto> findAllCars(CarFilterRequest filter, Pageable pageable);
}
