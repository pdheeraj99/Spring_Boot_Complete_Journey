package com.ecom.secure.api.repository;

import com.ecom.secure.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Login ayyetappudu user ni email tho find cheyadaniki ee method
    Optional<User> findByEmail(String email);
}
