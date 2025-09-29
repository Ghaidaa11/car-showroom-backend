package com.challenge.car_showroom.dtos;


import lombok.Data;

@Data
public class CarFilterRequest {
    private String vin;
    private String maker;
    private String model;
    private Integer modelYear;
    private String carShowroomName;
}
