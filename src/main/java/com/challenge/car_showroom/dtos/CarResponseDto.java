package com.challenge.car_showroom.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CarResponseDto {


    private String vin;
    private String maker;
    private String model;
    private Integer modelYear;
    private BigDecimal price;
    private ShowroomOfCarDto carShowroom;


    @Data
    public static class ShowroomOfCarDto {
        private String name;
        private String contactNumber;
    }


}
