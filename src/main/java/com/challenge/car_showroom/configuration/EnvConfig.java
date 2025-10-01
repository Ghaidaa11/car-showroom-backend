package com.challenge.car_showroom.configuration;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {

    @Bean
    public String jwtSecret() {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();
        return dotenv.get("JWT_SECRET");
    }

}
