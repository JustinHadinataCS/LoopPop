package com.LoopPop.LoopPop.LoopPop_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

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
    public  void addNew_LoopPop_User(LoopPop_User loopPop_user){
        Optional<LoopPop_User> LoopPop_Optional = loopPop_userRepository.findsLoopPopUserByEmail(loopPop_user.getEmail());
        if(LoopPop_Optional.isPresent()){
            throw new IllegalStateException("Email is not available");
        }
        loopPop_userRepository.save(loopPop_user);
    }

    public void LoopPop_deleteUser(Long LoopPop_UserId){
        boolean exists = loopPop_userRepository.existsById(LoopPop_UserId);
        if (!exists) {
            throw new IllegalStateException("User with id " + LoopPop_UserId + " does not exist!");
        }
        loopPop_userRepository.deleteById(LoopPop_UserId);
    }



}