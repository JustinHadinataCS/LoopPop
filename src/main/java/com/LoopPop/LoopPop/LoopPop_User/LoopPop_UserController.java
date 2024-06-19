package com.LoopPop.LoopPop.LoopPop_User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/looppop_user")
public class LoopPop_UserController {

    private final LoopPop_UserService loopPop_UserService;

    @Autowired
    public LoopPop_UserController(LoopPop_UserService loopPopUserService) {
        this.loopPop_UserService = loopPopUserService;
    }

    @GetMapping
    public List<LoopPop_User> GetLoopPopUser() {

        return  loopPop_UserService.GetLoopPopUser();
    }
    @PostMapping
    public void registerNew_LoopPop_User(@RequestBody LoopPop_User loopPop_user){
        loopPop_UserService.addNew_LoopPop_User(loopPop_user);
    }
    @PostMapping("/main/profile")
    public void updateProfile(@RequestBody LoopPop_User loopPop_user){
        loopPop_UserService.addNew_LoopPop_User(loopPop_user);
    }

    @DeleteMapping(path = "{LoopPop_UserId}")
    public void delete_LoopPop_user(@PathVariable("LoopPop_UserId") Long LoopPop_UserId){
        loopPop_UserService.LoopPop_deleteUser(LoopPop_UserId);
    }

    @PutMapping(path = "{LoopPop_UserId}")
    public void update_LoopPop_User(@PathVariable("LoopPop_UserId") Long LoopPop_UserId,
                                    @RequestBody LoopPop_User updatedLoopPop_User) {
        loopPop_UserService.update_LoopPop_User(LoopPop_UserId, updatedLoopPop_User);
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

        return "main";
    }

}

