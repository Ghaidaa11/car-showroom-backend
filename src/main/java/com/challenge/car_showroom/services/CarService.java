package com.challenge.car_showroom.services;

import com.challenge.car_showroom.dtos.CarCreateRequestDto;
import com.challenge.car_showroom.dtos.CarFilterRequest;
import com.challenge.car_showroom.dtos.CarResponseDto;
import com.challenge.car_showroom.exceptions.BadRequestException;
import com.challenge.car_showroom.mappers.CarShowroomMapper;
import com.challenge.car_showroom.models.Car;
import com.challenge.car_showroom.models.CarShowroom;
import com.challenge.car_showroom.repositories.CarRepo;
import com.challenge.car_showroom.repositories.CarShowroomRepo;
import com.challenge.car_showroom.specification.CarSpecification;
import com.challenge.car_showroom.utilities.PaginationResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepo carRepo;
    private final CarShowroomRepo carShowroomRepo;
    private final CarShowroomMapper carShowroomMapper;

    public CarService(final CarRepo carRepo,
                      final CarShowroomRepo carShowroomRepo,
                      final CarShowroomMapper carShowroomMapper) {
        this.carRepo = carRepo;
        this.carShowroomRepo = carShowroomRepo;
        this.carShowroomMapper = carShowroomMapper;
    }


    public void createCar(CarCreateRequestDto requestDto) {

        if (carRepo.existsByVinAndShowroom_IsDeleted(requestDto.getVin(), false)) {
            throw new BadRequestException("Car with this VIN already exists");
        }

        CarShowroom carShowroom = carShowroomRepo.findById(requestDto.getShowroomId()).orElseThrow(
                () -> new EntityNotFoundException("Showroom not found with id " + requestDto.getShowroomId()));

        if (carShowroom.isDeleted()) {
            throw new IllegalStateException("Please provide a valid car showroom.");
        }

        Car car = new Car(
                requestDto.getVin(),
                requestDto.getMaker(),
                requestDto.getModel(),
                requestDto.getModelYear(),
                requestDto.getPrice(),
                carShowroom);

        carRepo.save(car);
    }

    public PaginationResponse<CarResponseDto> listCars(CarFilterRequest filter, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Car> cars = carRepo.findAll(CarSpecification.filter(filter), pageable);

        Page<CarResponseDto> carResponseDtos = carShowroomMapper.mapCarsToResponseDtos(cars);

        return new PaginationResponse<>(carResponseDtos);
    }
}
