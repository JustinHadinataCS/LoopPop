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
import org.springframework.stereotype.Service;

@Controller
public class RegistrationController {

    private RegistrationService registrationService;
    private AppUserService appUserService;
    private List<AppUser> appUsers = new ArrayList<>();

    public RegistrationController(RegistrationService registrationService, AppUserService appUserService) {
        this.registrationService = registrationService;
        this.appUserService = appUserService;
    }

    @GetMapping("/index")
    public String index(Model model, Principal principal){
        UserDetails userDetails = appUserService.loadUserByUsername((String) principal.getName());
        model.addAttribute("userdetail", userDetails);
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model, RegistrationRequest request){
        model.addAttribute("user", request);
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model, RegistrationRequest request){
        model.addAttribute("user", request);
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
