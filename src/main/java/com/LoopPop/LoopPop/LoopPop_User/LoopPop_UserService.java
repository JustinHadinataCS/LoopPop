package com.LoopPop.LoopPop.LoopPop_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class LoopPop_UserService {

    public LoopPop_UserService(LoopPop_UserRepository loopPop_userRepository) {
        this.loopPop_userRepository = loopPop_userRepository;
    }

    private final LoopPop_UserRepository loopPop_userRepository;

    @Autowired
    public List<LoopPop_User> GetLoopPopUser() {
            return loopPop_userRepository.findAll();
    }
}