package com.LoopPop.LoopPop.LoopPop_User;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Indicates that this class handles HTTP requests and returns JSON responses
@RestController
// Base path for all endpoints in this controller
@RequestMapping(path = "api/v1/looppop_user")
public class LoopPop_UserController {

    private final LoopPop_UserService loopPop_UserService;

    // Constructor injection to initialize LoopPop_UserService
    @Autowired
    public LoopPop_UserController(LoopPop_UserService loopPopUserService) {
        this.loopPop_UserService = loopPopUserService;
    }

    // GET endpoint to fetch all LoopPop_User entities
    @GetMapping
    public List<LoopPop_User> GetLoopPopUser() {
        return loopPop_UserService.GetLoopPopUser();
    }

    // POST endpoint to register a new LoopPop_User
    @PostMapping
    public void registerNew_LoopPop_User(@RequestBody LoopPop_User loopPop_user) {
        loopPop_UserService.addNew_LoopPop_User(loopPop_user);
    }

    // POST endpoint to update profile of a LoopPop_User
    @PostMapping("/main/profile")
    public void updateProfile(@RequestBody LoopPop_User loopPop_user) {
        loopPop_UserService.addNew_LoopPop_User(loopPop_user);
    }

    // DELETE endpoint to delete a LoopPop_User by ID
    @DeleteMapping(path = "{LoopPop_UserId}")
    public void delete_LoopPop_user(@PathVariable("LoopPop_UserId") Long LoopPop_UserId) {
        loopPop_UserService.LoopPop_deleteUser(LoopPop_UserId);
    }

    // PUT endpoint to update details of a LoopPop_User
    @PutMapping(path = "{LoopPop_UserId}")
    public void update_LoopPop_User(@PathVariable("LoopPop_UserId") Long LoopPop_UserId,
                                    @RequestBody LoopPop_User updatedLoopPop_User) {
        loopPop_UserService.update_LoopPop_User(LoopPop_UserId, updatedLoopPop_User);
    }

    // POST endpoint to update profile of a LoopPop_User with authentication
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

    // GET endpoint to fetch main page with user details
    @GetMapping("/main")
    public String mainPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            // Handle unauthorized access
            return "redirect:/login";
        }

        String email = userDetails.getUsername();
        LoopPop_User existingUser = loopPop_UserService.findByEmail(email);

        if (existingUser != null) {
            model.addAttribute("loopPopUser", existingUser);
        }

        // Return the main page template name
        return "main";
    }

}


