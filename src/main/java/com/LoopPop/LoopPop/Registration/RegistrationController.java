package com.LoopPop.LoopPop.Registration;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.LoopPop.LoopPop.AppUser.AppUser;
import com.LoopPop.LoopPop.AppUser.AppUserRole;
import com.LoopPop.LoopPop.AppUser.AppUserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.LoopPop.LoopPop.AppUser.AppUserRole.ADMIN;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;
    private final AppUserService appUserService;

    public RegistrationController(RegistrationService registrationService, AppUserService appUserService) {
        this.registrationService = registrationService;
        this.appUserService = appUserService;
    }

    @GetMapping("/index")
    public String index(Model model, Principal principal){
        UserDetails userDetails = appUserService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetail", userDetails);
        return "index";
    }

    @GetMapping("/test")
    public String timetable(){
        return "test";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new RegistrationRequest());
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new RegistrationRequest());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationSave(@ModelAttribute("user") RegistrationRequest request) {
        boolean userExists = registrationService.register(request);
        if(userExists){
            return "redirect:/registration?error";
        }
        return "redirect:/registration?success";
    }
}
