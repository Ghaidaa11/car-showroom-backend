package com.challenge.car_showroom.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListCarShowroomResponseDto {

    private Long id;

    private String name;

    private String commercialRegistrationNumber;

    private String contactNumber;

    public ListCarShowroomResponseDto(final Long id,
                                      final String name,
                                      final String commercialRegistrationNumber,
                                      final String contactNumber) {
        this.id = id;
        this.name = name;
        this.commercialRegistrationNumber = commercialRegistrationNumber;
        this.contactNumber = contactNumber;
    }
}
