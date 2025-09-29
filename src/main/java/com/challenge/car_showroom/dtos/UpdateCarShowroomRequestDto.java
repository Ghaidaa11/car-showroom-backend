package com.challenge.car_showroom.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarShowroomRequestDto {

    private Long managerId;

    @Pattern(regexp = "\\d{1,15}", message = "Contact number must be numeric and up to 15 digits")
    private String contactNumber;

    @Size(max = 255, message = "Address must be at most 255 characters")
    private String address;

}
