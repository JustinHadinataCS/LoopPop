package com.LoopPop.LoopPop.LoopPop_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/looppop_user")
public class LoopPop_UserController {

    private final LoopPop_UserService loopPopUserService;

    @Autowired
    public LoopPop_UserController(LoopPop_UserService loopPopUserService) {
        this.loopPopUserService = loopPopUserService;
    }

    @GetMapping
    public List<LoopPop_User> GetLoopPopUser() {
        return loopPopUserService.GetLoopPopUser();
    }
}