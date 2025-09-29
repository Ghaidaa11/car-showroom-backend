package com.challenge.car_showroom.dtos;

import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    public UserResponseDto(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserResponseDto() {}
}
