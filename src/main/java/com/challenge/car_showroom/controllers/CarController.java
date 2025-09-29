package com.challenge.car_showroom.controllers;

import com.challenge.car_showroom.dtos.CarCreateRequestDto;
import com.challenge.car_showroom.dtos.CarFilterRequest;
import com.challenge.car_showroom.dtos.CarResponseDto;
import com.challenge.car_showroom.services.CarService;
import com.challenge.car_showroom.utilities.PaginationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<Map<String, String>> createCar(@RequestBody @Valid final CarCreateRequestDto requestDto) {
        carService.createCar(requestDto);
        Map<String, String> response = Map.of("message", "Car created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/list")
    public PaginationResponse<CarResponseDto> listCars(
            @RequestBody final CarFilterRequest filterRequest,
            @RequestParam(name = "page", defaultValue = "1") final int page,
            @RequestParam(name = "size", defaultValue = "10") final int size
            ) {
       return carService.listCars(filterRequest, page -1, size);
    }
}
