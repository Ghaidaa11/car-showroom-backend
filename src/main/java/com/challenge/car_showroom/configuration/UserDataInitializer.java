package com.challenge.car_showroom.configuration;

import com.challenge.car_showroom.enums.UserRoles;
import com.challenge.car_showroom.models.User;
import com.challenge.car_showroom.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class UserDataInitializer implements CommandLineRunner {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<User> defaultUsers = List.of(
                createUser("Mohammed", "Ali", "555555555", "123456", UserRoles.MANAGER),
                createUser("Nasser", "Fahad", "555555559", "123456", UserRoles.MANAGER),
                createUser("User", "Normal", "555555556", "123456", UserRoles.USER)
        );

        for (User user : defaultUsers) {
            if (userRepository.findByPhone(user.getPhone()).isEmpty()) {
                userRepository.save(user);
                System.out.println(" -------- inserted user with phone " + user.getPhone());
            } else {
                System.out.println(" -------- user with phone " + user.getPhone() + " already exists.");
            }
        }
    }

    private User createUser(String firstName, String lastName, String phone, String rawPassword, UserRoles role) {
        return new User(firstName, lastName, phone, role, passwordEncoder.encode(rawPassword));
    }


}
