package com.challenge.car_showroom.mappers;

import com.challenge.car_showroom.dtos.UserResponseDto;
import com.challenge.car_showroom.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto userToUserResponseDto(User user);
}
