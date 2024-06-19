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

@Controller
@RequestMapping("/user")
public class UserProfileController {

    private final LoopPop_UserService userService;

    @Autowired
    public UserProfileController(LoopPop_UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login"; // Redirect to login if user is not authenticated
        }

        LoopPop_User user = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("user", user);
        return "profile"; // Ensure this is the correct template name
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute LoopPop_User updatedUser, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login"; // Redirect to login if user is not authenticated
        }

        LoopPop_User user = userService.findByEmail(userDetails.getUsername());
        if (user != null) {
            user.setHobby(updatedUser.getHobby());
            user.setFavoriteMusic(updatedUser.getFavoriteMusic());
            userService.update_LoopPop_User(user.getId(), user);
        }

        return "redirect:/user/profile"; // Redirect to the profile page after update
    }
}
