package com.LoopPop.LoopPop.LoopPop_User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Indicates that this interface is a repository for LoopPop_User entities
@Repository
public interface LoopPop_UserRepository extends JpaRepository<LoopPop_User, Long> {

    // Custom query to find a LoopPop_User by email using JPQL
    @Query("SELECT l FROM LoopPop_User l WHERE l.email = ?1")
    Optional<LoopPop_User> findsLoopPopUserByEmail(String email);
}
