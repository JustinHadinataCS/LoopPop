package com.LoopPop.LoopPop.Registration;

import com.LoopPop.LoopPop.LoopPop_User.LoopPop_User;
import com.LoopPop.LoopPop.LoopPop_User.LoopPop_UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final LoopPop_UserService loopPop_UserService; // Add this line

    public RegistrationController(RegistrationService registrationService, AppUserService appUserService, LoopPop_UserService loopPop_UserService) { // Modify the constructor
        this.registrationService = registrationService;
        this.appUserService = appUserService;
        this.loopPop_UserService = loopPop_UserService; // Add this line
    }


    @GetMapping("/")
    public String defaultPage() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new RegistrationRequest());
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new RegistrationRequest());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationSave(@ModelAttribute("user") RegistrationRequest request) {
        boolean userExists = registrationService.register(request);
        if (userExists) {
            return "redirect:/registration?error";
        }
        return "redirect:/registration?success";
    }

    @GetMapping("/main")
    public String mainPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            // Handle unauthorized access
            return "redirect:/login";
        }

        String email = userDetails.getUsername();
        LoopPop_User existingUser = loopPop_UserService.findByEmail(email);

        if (existingUser != null) {
            model.addAttribute("firstname", existingUser.getName());
            model.addAttribute("loopPopUser", existingUser); // Add this line
        }

        return "main";
    }


    // Add this method to get the currently authenticated user's username
    private String getCurrentAuthenticatedUsername() {
        // Logic to get the authenticated user's username
        // This is just an example, adapt it to your security configuration
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return "Guest";
    }
    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute("loopPopUser") LoopPop_User updatedLoopPopUser,
                                @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            // Handle unauthorized access
            return "redirect:/login";
        }

        String email = userDetails.getUsername();
        LoopPop_User existingUser = loopPop_UserService.findByEmail(email);

        if (existingUser != null) {
            Long userId = existingUser.getId();
            loopPop_UserService.update_LoopPop_User(userId, updatedLoopPopUser);
        }

        // Redirect to a success page or display a success message
        return "redirect:/main?success";
    }


}

