package com.challenge.car_showroom.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarCreateRequestDto {

    @NotBlank(message = "VIN is required")
    @Size(max = 25, message = "VIN must not exceed 25 characters")
    private String vin;

    @NotBlank(message = "Maker is required")
    @Size(max = 25, message = "Maker must not exceed 25 characters")
    private String maker;

    @NotBlank(message = "Model is required")
    @Size(max = 25, message = "Model must not exceed 25 characters")
    private String model;

    @NotNull(message = "Model year is required")
    @Digits(integer = 4, fraction = 0, message = "Model year must be a 4-digit number")
    private Integer modelYear;

    @NotNull(message = "Price is required")
    private BigDecimal price;

    @NotNull(message = "Car showroom ID is required")
    private Long showroomId;

}
