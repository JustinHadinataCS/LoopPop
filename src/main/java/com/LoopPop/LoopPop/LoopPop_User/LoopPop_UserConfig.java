package com.LoopPop.LoopPop.LoopPop_User;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class LoopPop_UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(LoopPop_UserRepository repository){
        return args -> {
            LoopPop_User brad = new LoopPop_User(
                    1L,
                    "Brad", "Brad@gmail.com",
                    LocalDate.of(2000, Month.DECEMBER,
                            5),
                    21
            );
            LoopPop_User jacob = new LoopPop_User(
                    "Jacob", "Jacob@gmail.com",
                    LocalDate.of(2003, Month.DECEMBER,
                            29),
                    20
            );
            repository.saveAll(
                    List.of(brad, jacob)
            );
        };
    }
}
