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

// Mark this class as a Spring MVC controller
@Controller
public class RegistrationController {

    // Dependencies that will be injected
    private final RegistrationService registrationService;
    private final AppUserService appUserService;
    private final LoopPop_UserService loopPop_UserService;

    // Constructor for dependency injection
    public RegistrationController(RegistrationService registrationService,
                                  AppUserService appUserService,
                                  LoopPop_UserService loopPop_UserService) {
        this.registrationService = registrationService;
        this.appUserService = appUserService;
        this.loopPop_UserService = loopPop_UserService;
    }

    // Endpoint to redirect the root URL to the index page
    @GetMapping("/")
    public String defaultPage() {
        return "redirect:/index";
    }

    // Endpoint to serve the index page
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // Endpoint to serve the login page
    @GetMapping("/login")
    public String login(Model model) {
        // Add a new RegistrationRequest object to the model
        model.addAttribute("user", new RegistrationRequest());
        return "login";
    }

    // Endpoint to serve the registration page
    @GetMapping("/registration")
    public String registration(Model model) {
        // Add a new RegistrationRequest object to the model
        model.addAttribute("user", new RegistrationRequest());
        return "registration";
    }

    // Endpoint to handle the registration form submission
    @PostMapping("/registration")
    public String registrationSave(@ModelAttribute("user") RegistrationRequest request) {
        boolean userExists = registrationService.register(request);
        if (userExists) {
            // Redirect to the registration page with an error query parameter if the user already exists
            return "redirect:/registration?error";
        }
        // Redirect to the registration page with a success query parameter if the registration is successful
        return "redirect:/registration?success";
    }

    // Endpoint to serve the main page
    @GetMapping("/main")
    public String mainPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            // Redirect to the login page if the user is not authenticated
            return "redirect:/login";
        }

        String email = userDetails.getUsername();
        LoopPop_User existingUser = loopPop_UserService.findByEmail(email);

        if (existingUser != null) {
            // Add the user's first name and user object to the model
            model.addAttribute("firstname", existingUser.getName());
            model.addAttribute("loopPopUser", existingUser);
        }

        return "main";
    }

    // Private method to get the currently authenticated user's username
    private String getCurrentAuthenticatedUsername() {
        // Get the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken)) {
            // Return the authenticated user's username
            return authentication.getName();
        }
        return "Guest";
    }

    // Endpoint to handle the profile update form submission
    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute("loopPopUser")
                                    LoopPop_User updatedLoopPopUser,
                                @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            // Redirect to the login page if the user is not authenticated
            return "redirect:/login";
        }

        String email = userDetails.getUsername();
        LoopPop_User existingUser = loopPop_UserService.findByEmail(email);

        if (existingUser != null) {
            // Update the user's profile with the provided data
            Long userId = existingUser.getId();
            loopPop_UserService.update_LoopPop_User(userId, updatedLoopPopUser);
        }

        // Redirect to the main page with a success query parameter
        return "redirect:/main?success";
    }

    // Endpoint to serve the profile page for a specific user
    @GetMapping("/profile/{userId}")
    public String getProfilePage(@PathVariable Long userId, Model model) {
        // Find the user by their ID and add them to the model
        LoopPop_User user = loopPop_UserService.findById(userId);
        model.addAttribute("user", user);
        return "profile";
    }
}
