package com.security.learn.learn_usernamepwdauthfilter.repository;

import com.security.learn.learn_usernamepwdauthfilter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
