package com.challenge.car_showroom.repositories;

import com.challenge.car_showroom.dtos.ListCarShowroomResponseDto;
import com.challenge.car_showroom.models.CarShowroom;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CarShowroomRepo extends JpaRepository<CarShowroom, Long> {

    boolean existsByCommercialRegistrationNumber(String commercialRegistrationNumber);

    @Query("select new com.challenge.car_showroom.dtos.ListCarShowroomResponseDto(c.id, c.name, c.commercialRegistrationNumber, c.contactNumber) " +
            "from CarShowroom c where c.isDeleted = false ")
    Page<ListCarShowroomResponseDto> findAllShowrooms(Pageable pageable);

    @Query("select new com.challenge.car_showroom.dtos.ListCarShowroomResponseDto(c.id, c.name, c.commercialRegistrationNumber, c.contactNumber) " +
            "from CarShowroom c where c.isDeleted = false")
    List<ListCarShowroomResponseDto> findAllShowrooms();
}
