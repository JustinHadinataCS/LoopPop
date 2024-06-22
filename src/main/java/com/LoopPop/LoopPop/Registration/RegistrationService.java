package com.LoopPop.LoopPop.Registration;



import com.LoopPop.LoopPop.AppUser.AppUser;
import com.LoopPop.LoopPop.AppUser.AppUserRole;
import com.LoopPop.LoopPop.AppUser.AppUserService;
import org.springframework.stereotype.Service;

// Mark this class as a service component in the Spring framework
@Service
public class RegistrationService {

    // Dependency that will be injected
    private final AppUserService appUserService;

    // Constructor for dependency injection
    public RegistrationService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    // Method to register a new user
    public boolean register(RegistrationRequest request) {
        // Create a new AppUser object using the data from the registration request
        AppUser newUser = new AppUser(
                request.getFirstname(),
                request.getLastname(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER // Set the user role to USER
        );

        // Delegate the user sign-up process to the appUserService
        return appUserService.signUpUser(newUser);
    }
}
