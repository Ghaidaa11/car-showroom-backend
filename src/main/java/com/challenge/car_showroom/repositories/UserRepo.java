package com.challenge.car_showroom.repositories;

import com.challenge.car_showroom.dtos.UserResponseDto;
import com.challenge.car_showroom.enums.UserRoles;
import com.challenge.car_showroom.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query("select new com.challenge.car_showroom.dtos.UserResponseDto(u.id, u.firstName, u.lastName) from User u where u.role = :role")
    List<UserResponseDto> findByRole(UserRoles role);
}
