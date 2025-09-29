package com.challenge.car_showroom.controllers;

import com.challenge.car_showroom.dtos.CarShowroomRequestDto;
import com.challenge.car_showroom.dtos.CarShowroomResponseDto;
import com.challenge.car_showroom.dtos.ListCarShowroomResponseDto;
import com.challenge.car_showroom.dtos.UpdateCarShowroomRequestDto;
import com.challenge.car_showroom.utilities.PaginationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.challenge.car_showroom.services.CarShowroomService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/car-showroom")
public class CarShowroomController {

    private final CarShowroomService carShowroomService;

    public CarShowroomController(CarShowroomService carShowroomService) {
        this.carShowroomService = carShowroomService;
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> createCarShowroom(
            @RequestBody @Validated final CarShowroomRequestDto carShowroomRequestDto) {
        carShowroomService.createCarShowroom(carShowroomRequestDto);
        Map<String, String> response = Map.of("message", "Car showroom created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public PaginationResponse<ListCarShowroomResponseDto> getAllCarShowrooms(
            @RequestParam(name = "page") final int page,
            @RequestParam(name = "size", defaultValue = "10") final int size,
            @RequestParam(name = "sortBy", defaultValue = "createdAt") final String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "desc") final String sortDir
    ) {
        return carShowroomService.listCarShowrooms(page - 1, size, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public CarShowroomResponseDto getCarShowroomById(
            @PathVariable final Long id
    ) {
        return carShowroomService.getCarShowroom(id);
    }

    @PatchMapping("/{id}")
    public CarShowroomResponseDto updateCarShowroom(
            @PathVariable final Long id,
            @RequestBody @Validated final UpdateCarShowroomRequestDto updateCarShowroomRequestDto
    ) {
        return carShowroomService.updateCarShowroom(updateCarShowroomRequestDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarShowroom(
            @PathVariable final Long id
    ) {
        carShowroomService.deleteCarShowroom(id);
        return ResponseEntity.ok("Car showroom deleted successfully");
    }

    @GetMapping("/list")
    public List<ListCarShowroomResponseDto> getAllCarShowrooms() {
        return carShowroomService.listAllShowrooms();
    }



}
