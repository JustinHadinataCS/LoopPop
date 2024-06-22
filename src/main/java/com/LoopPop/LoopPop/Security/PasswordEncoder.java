package com.LoopPop.LoopPop.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Mark this class as a configuration class for Spring
@Configuration
public class PasswordEncoder {

    // Define a bean for BCryptPasswordEncoder
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        // Create and return a new instance of BCryptPasswordEncoder
        return new BCryptPasswordEncoder();
    }
}
