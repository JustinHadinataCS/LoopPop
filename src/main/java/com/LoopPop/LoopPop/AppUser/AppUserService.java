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
@Service
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final LoopPop_UserService loopPop_UserService;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, LoopPop_UserService loopPop_UserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.loopPop_UserService = loopPop_UserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public AppUser findUserByEmail(String email) {
        return appUserRepository.findByEmail(email).orElse(null);
    }

    public boolean signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository.findByEmail(appUser.getUsername()).isPresent();

        if (userExists) {
            return userExists;
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);

        // Create a new LoopPop_User entity with the same email and name as the AppUser entity
        LoopPop_User loopPop_User = new LoopPop_User();
        loopPop_User.setEmail(appUser.getUsername());
        loopPop_User.setName(appUser.getFirstName() + " " + appUser.getLastName());
        loopPop_User.setDob(LocalDate.now()); // Set a default value for the dob field
        loopPop_UserService.addNew_LoopPop_User(loopPop_User);

        return userExists;
    }
}
