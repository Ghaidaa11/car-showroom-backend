package com.challenge.car_showroom.mappers;

import com.challenge.car_showroom.dtos.CarResponseDto;
import com.challenge.car_showroom.dtos.CarShowroomRequestDto;
import com.challenge.car_showroom.dtos.CarShowroomResponseDto;
import com.challenge.car_showroom.models.Car;
import com.challenge.car_showroom.models.CarShowroom;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;


@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CarShowroomMapper {


    CarShowroom mapCarShowroomRequestDtoToCarShowroom(CarShowroomRequestDto carShowroomRequestDto);

    CarShowroomResponseDto mapCarShowroomToCarShowroomResponseDto(CarShowroom carShowroom);

    CarResponseDto.ShowroomOfCarDto mapToCarShowroomResponseDto(CarShowroom carShowroom);

    CarResponseDto toCarResponseDto(Car car);

    default CarResponseDto mapCarToResponseDto(Car car) {
        CarResponseDto carResponseDto = this.toCarResponseDto(car);
        carResponseDto.setCarShowroom(this.mapToCarShowroomResponseDto(car.getShowroom()));
        return carResponseDto;
    }

    default Page<CarResponseDto> mapCarsToResponseDtos(Page<Car> cars) {
        return cars.map(this::mapCarToResponseDto);
    }
}
