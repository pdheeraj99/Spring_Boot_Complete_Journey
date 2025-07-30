package com.security.learn.learn_springwithgoogleoauth.service;

import com.security.learn.learn_springwithgoogleoauth.entity.User;
import com.security.learn.learn_springwithgoogleoauth.enums.Role;
import com.security.learn.learn_springwithgoogleoauth.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User findOrCreateUser(String email, String name) {
        // Check if user already exists in our database
        return userRepository.findByEmail(email)
                .orElseGet(() -> {
                    // If user doesn't exist, create a new one
                    User newUser = User.builder()
                            .email(email)
                            .firstName(name)
                            // Social login users don't have a password with us, so we set a random one
                            .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                            .role(Role.USER)
                            .build();
                    return userRepository.save(newUser);
                });
    }
}
