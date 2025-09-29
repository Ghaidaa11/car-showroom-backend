package com.challenge.car_showroom.services;


import com.challenge.car_showroom.dtos.CarShowroomRequestDto;
import com.challenge.car_showroom.dtos.CarShowroomResponseDto;
import com.challenge.car_showroom.dtos.ListCarShowroomResponseDto;
import com.challenge.car_showroom.dtos.UpdateCarShowroomRequestDto;
import com.challenge.car_showroom.exceptions.BadRequestException;
import com.challenge.car_showroom.utilities.PaginationResponse;
import jakarta.persistence.EntityNotFoundException;
import com.challenge.car_showroom.mappers.CarShowroomMapper;
import com.challenge.car_showroom.models.CarShowroom;
import com.challenge.car_showroom.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.challenge.car_showroom.repositories.CarShowroomRepo;
import com.challenge.car_showroom.repositories.UserRepo;

import java.util.List;
import java.util.Set;

@Service
public class CarShowroomService {

    private final CarShowroomRepo carShowroomRepo;
    private final UserRepo userRepo;
    private final CarShowroomMapper carShowroomMapper;

    public CarShowroomService(final CarShowroomRepo carShowroomRepo,
                              final UserRepo userRepo,
                              final CarShowroomMapper carShowroomMapper) {
        this.carShowroomRepo = carShowroomRepo;
        this.userRepo = userRepo;
        this.carShowroomMapper = carShowroomMapper;
    }

    public void createCarShowroom(final CarShowroomRequestDto carShowroomRequestDto) {

        if (carShowroomRepo.existsByCommercialRegistrationNumber(carShowroomRequestDto.getCommercialRegistrationNumber())) {
            throw new BadRequestException("Commercial registration number must be unique");
        }

        User user = userRepo.findById(carShowroomRequestDto.getManagerId()).orElseThrow(
                () -> new EntityNotFoundException("manager not found with id " + carShowroomRequestDto.getManagerId()));


        CarShowroom carShowroom = carShowroomMapper.mapCarShowroomRequestDtoToCarShowroom(carShowroomRequestDto);
        carShowroom.setManager(user);

        carShowroomRepo.save(carShowroom);

    }

    public PaginationResponse<ListCarShowroomResponseDto> listCarShowrooms(final int page,
                                                                           final int size,
                                                                           final String sortBy,
                                                                           final String sortDir) {

        Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        final Set<String> ALLOWED_SORT_FIELDS = Set.of("name", "commercialRegistrationNumber", "contactNumber");

        String validatedSortBy = ALLOWED_SORT_FIELDS.contains(sortBy) ? sortBy : "createdAt";

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, validatedSortBy));

        Page<ListCarShowroomResponseDto> carShowroomResponseDtos = carShowroomRepo.findAllShowrooms(pageable);

        return new PaginationResponse<>(carShowroomResponseDtos);
    }

    public CarShowroomResponseDto getCarShowroom(final Long id) {
        CarShowroom carShowroom = carShowroomRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Showroom not found with id " + id));
        CarShowroomResponseDto carShowroomResponseDto = carShowroomMapper.mapCarShowroomToCarShowroomResponseDto(carShowroom);
        return carShowroomResponseDto;
    }

    public CarShowroomResponseDto updateCarShowroom(final UpdateCarShowroomRequestDto updateCarShowroomRequestDto,
                                                    final Long id) {
        CarShowroom carShowroom = carShowroomRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Showroom not found with id " + id));

        if (updateCarShowroomRequestDto.getContactNumber() != null) {
            carShowroom.setContactNumber(updateCarShowroomRequestDto.getContactNumber());
        }

        if (updateCarShowroomRequestDto.getAddress() != null) {
            carShowroom.setAddress(updateCarShowroomRequestDto.getAddress());
        }

        if (updateCarShowroomRequestDto.getManagerId() != null) {
            User user = userRepo.findById(updateCarShowroomRequestDto.getManagerId()).orElseThrow(
                    () -> new EntityNotFoundException("manager not found with id " + updateCarShowroomRequestDto.getManagerId()));
            carShowroom.setManager(user);
        }

        CarShowroom updatedShowroom =  carShowroomRepo.save(carShowroom);

        return carShowroomMapper.mapCarShowroomToCarShowroomResponseDto(updatedShowroom);
    }

    public void deleteCarShowroom(final Long id) {
        CarShowroom carShowroom = carShowroomRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Showroom not found with id " + id));

        carShowroomRepo.deleteById(carShowroom.getId());
    }

    public List<ListCarShowroomResponseDto> listAllShowrooms() {
        return carShowroomRepo.findAllShowrooms();
    }
}
