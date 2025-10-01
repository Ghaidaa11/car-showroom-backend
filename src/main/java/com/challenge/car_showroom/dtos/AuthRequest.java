package com.challenge.car_showroom.dtos;

import lombok.Data;

@Data
public class AuthRequest {
    private String phone;
    private String password;
}
