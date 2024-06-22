package com.LoopPop.LoopPop.LoopPop_User;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

// Mark this class as a configuration class for Spring
@Configuration
public class LoopPop_UserConfig {

    // Define a bean for initializing data using CommandLineRunner
    @Bean
    CommandLineRunner commandLineRunner(LoopPop_UserRepository repository) {
        return args -> {
            // Create instances of LoopPop_User with sample data
            LoopPop_User brad = new LoopPop_User(
                    1L, // ID
                    "Justin", // Name
                    "Brad@gmail.com", // Email
                    "Games", // Hobby
                    "Cranberries", // Favorite music
                    LocalDate.of(2000, Month.DECEMBER, 5) // Date of birth
            );

            LoopPop_User jacob = new LoopPop_User(
                    "Jacob", // Name
                    "Jacob@gmail.com", // Email
                    "Basketball", // Hobby
                    "Gorrilaz", // Favorite music
                    LocalDate.of(2003, Month.DECEMBER, 29) // Date of birth
            );

            // Save the created instances to the repository
            repository.saveAll(List.of(brad, jacob));
        };
    }
}
