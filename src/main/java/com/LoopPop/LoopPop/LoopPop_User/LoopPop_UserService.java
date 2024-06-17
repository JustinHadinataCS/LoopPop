package com.LoopPop.LoopPop.LoopPop_User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoopPop_UserService {

    private final LoopPop_UserRepository loopPop_userRepository;

    @Autowired
    public LoopPop_UserService(LoopPop_UserRepository loopPop_userRepository) {
        this.loopPop_userRepository = loopPop_userRepository;
    }

    public List<LoopPop_User> GetLoopPopUser() {
        return loopPop_userRepository.findAll();
    }

    public void addNew_LoopPop_User(LoopPop_User loopPop_user) {
        Optional<LoopPop_User> loopPopUserOptional = loopPop_userRepository.findsLoopPopUserByEmail(loopPop_user.getEmail());
        if (loopPopUserOptional.isPresent()) {
            throw new IllegalStateException("Email is not available");
        }
        loopPop_userRepository.save(loopPop_user);
    }

    public void LoopPop_deleteUser(Long LoopPop_UserId) {
        boolean exists = loopPop_userRepository.existsById(LoopPop_UserId);
        if (!exists) {
            throw new IllegalStateException("User with id " + LoopPop_UserId + " does not exist!");
        }
        loopPop_userRepository.deleteById(LoopPop_UserId);
    }

    @Transactional
    public void update_LoopPop_User(Long LoopPop_UserId, LoopPop_User updatedLoopPop_User) {
        LoopPop_User loopPop_user = loopPop_userRepository.findById(LoopPop_UserId)
                .orElseThrow(() -> new IllegalStateException("User with id " + LoopPop_UserId + " does not exist!"));

        if (updatedLoopPop_User.getName() != null && !Objects.equals(loopPop_user.getName(), updatedLoopPop_User.getName())) {
            loopPop_user.setName(updatedLoopPop_User.getName());
        }

        if (updatedLoopPop_User.getEmail() != null && !Objects.equals(loopPop_user.getEmail(), updatedLoopPop_User.getEmail())) {
            Optional<LoopPop_User> loopPopUserOptional = loopPop_userRepository.findsLoopPopUserByEmail(updatedLoopPop_User.getEmail());
            if (loopPopUserOptional.isPresent()) {
                throw new IllegalStateException("Email is not available");
            }
            loopPop_user.setEmail(updatedLoopPop_User.getEmail());
        }
    }

    public LoopPop_User findByEmail(String email) {
        return loopPop_userRepository.findsLoopPopUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
