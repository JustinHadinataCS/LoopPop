package com.LoopPop.LoopPop.Registration;



import com.LoopPop.LoopPop.AppUser.AppUser;
import com.LoopPop.LoopPop.AppUser.AppUserRole;
import com.LoopPop.LoopPop.AppUser.AppUserService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final AppUserService appUserService;

    public RegistrationService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    public boolean register(RegistrationRequest request){
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstname(),
                        request.getLastname(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}
