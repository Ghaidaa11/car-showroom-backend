package com.challenge.car_showroom.controllers;

import com.challenge.car_showroom.dtos.UserResponseDto;
import com.challenge.car_showroom.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/managers")
    public List<UserResponseDto> getManagers() {
        return userService.getManagers();
    }

}
