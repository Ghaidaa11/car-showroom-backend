package com.challenge.car_showroom.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarShowroomResponseDto {

    private Long id;

    private String name;

    private String commercialRegistrationNumber;

    private UserResponseDto manager;

    private String contactNumber;

    private String address;

}
