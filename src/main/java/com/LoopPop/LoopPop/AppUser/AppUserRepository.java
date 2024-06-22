package com.LoopPop.LoopPop.AppUser;


import com.fasterxml.jackson.annotation.OptBoolean;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// Mark this interface as a Spring Data repository
@Repository
// Indicate that the methods of this repository should be executed within a transaction context
// with read-only transactions by default
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    // Define a method to find an AppUser by their email address
    // Spring Data JPA will automatically implement this method based on the method name
    Optional<AppUser> findByEmail(String email);
}
