package com.LoopPop.LoopPop.AppUser;

import com.LoopPop.LoopPop.LoopPop_User.LoopPop_User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.LoopPop.LoopPop.LoopPop_User.LoopPop_UserService;

import java.time.LocalDate;
// Mark this class as a Spring service component
@Service
public class AppUserService implements UserDetailsService {

    // Constant message template for user not found exception
    private final static String USER_NOT_FOUND = "user with email %s not found";

    // Dependencies that will be injected
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;    private final LoopPop_UserService loopPop_UserService;

    // Constructor for dependency injection
    @Autowired
    public AppUserService(AppUserRepository appUserRepository, LoopPop_UserService loopPop_UserService,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.loopPop_UserService = loopPop_UserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // Override the method to load user details by username (email in this case)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Find the user by email or throw an exception if not found
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    // Method to find a user by email and return the AppUser object or null if not found
    public AppUser findUserByEmail(String email) {
        return appUserRepository.findByEmail(email).orElse(null);
    }

    // Method to sign up a new user
    public boolean signUpUser(AppUser appUser) {
        // Check if the user already exists by email
        boolean userExists = appUserRepository.findByEmail(appUser.getUsername()).isPresent();

        if (userExists) {
            return userExists; // If user exists, return true
        }

        // Encode the user's password
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        // Save the new user in the repository
        appUserRepository.save(appUser);

        // Create a new LoopPop_User entity with the same email and name as the AppUser entity
        LoopPop_User loopPop_User = new LoopPop_User();
        loopPop_User.setEmail(appUser.getUsername());
        loopPop_User.setName(appUser.getFirstName() + " " + appUser.getLastName());
        loopPop_User.setDob(LocalDate.now()); // Set a default value for the dob field

        // Save the new LoopPop_User entity
        loopPop_UserService.addNew_LoopPop_User(loopPop_User);

        return userExists; // Return false indicating the user did not exist previously
    }
}
