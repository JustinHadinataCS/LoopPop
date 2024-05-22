package com.LoopPop.LoopPop.LoopPop_User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoopPop_UserRepository
        extends JpaRepository<LoopPop_User, Long> {

    @Query("SELECT l FROM LoopPop_User l Where l.email = ?1")
    Optional<LoopPop_User> findsLoopPopUserByEmail(String email);
}

