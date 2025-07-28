package com.security.learn.learn_jwt2.repository;

import com.security.learn.learn_jwt2.entity.RefreshToken;
import com.security.learn.learn_jwt2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);

    // ADD THIS NEW METHOD
    Optional<RefreshToken> findByUser(User user);
}