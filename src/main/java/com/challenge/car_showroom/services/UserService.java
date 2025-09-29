package com.challenge.car_showroom.services;

import com.challenge.car_showroom.dtos.UserResponseDto;
import com.challenge.car_showroom.enums.UserRoles;
import com.challenge.car_showroom.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(final UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<UserResponseDto> getManagers() {
        return userRepo.findByRole(UserRoles.Manager);
    }
}
