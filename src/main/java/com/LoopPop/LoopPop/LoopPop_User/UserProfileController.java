package com.LoopPop.LoopPop.LoopPop_User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.LoopPop.LoopPop.LoopPop_User.LoopPop_User;
import com.LoopPop.LoopPop.LoopPop_User.LoopPop_UserService;

@Controller
@RequestMapping("/user")
public class UserProfileController {

    private final LoopPop_UserService userService;

    // Constructor injection to initialize LoopPop_UserService
    @Autowired
    public UserProfileController(LoopPop_UserService userService) {
        this.userService = userService;
    }

    // Handler method for displaying user profile
    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login"; // Redirect to login if user is not authenticated
        }

        // Fetch user details based on authenticated username
        LoopPop_User user = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("user", user); // Add user object to the model
        return "profile"; // Return the profile template (make sure this template exists)
    }

    // Handler method for updating user profile
    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute LoopPop_User updatedUser, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login"; // Redirect to login if user is not authenticated
        }

        // Fetch user details based on authenticated username
        LoopPop_User user = userService.findByEmail(userDetails.getUsername());
        if (user != null) {
            // Update user's hobby and favorite music based on form submission
            user.setHobby(updatedUser.getHobby());
            user.setFavoriteMusic(updatedUser.getFavoriteMusic());
            userService.update_LoopPop_User(user.getId(), user); // Update user details in the database
        }

        return "redirect:/user/profile"; // Redirect to the profile page after update
    }
}
