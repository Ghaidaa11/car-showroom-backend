package com.challenge.car_showroom.dtos;


import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarShowroomRequestDto {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @NotBlank(message = "Commercial registration number is required")
    @Pattern(regexp = "\\d{10}", message = "Commercial registration number must be exactly 10 digits")
    private String commercialRegistrationNumber;

    private Long managerId;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "\\d{1,15}", message = "Contact number must be numeric and up to 15 digits")
    private String contactNumber;

    @Size(max = 255, message = "Address must be at most 255 characters")
    private String address;


    public void setName(String name) {
        this.name = name;
    }

    public void setCommercialRegistrationNumber(String commercialRegistrationNumber) {
        this.commercialRegistrationNumber = commercialRegistrationNumber;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getCommercialRegistrationNumber() {
        return commercialRegistrationNumber;
    }

    public Long getManagerId() {
        return managerId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }
}
