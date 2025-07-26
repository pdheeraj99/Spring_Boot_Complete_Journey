package com.learn.spring.learnspringsecurity.security.stateful.usernamepasswordauthenticationfilter.service;

import com.learn.spring.learnspringsecurity.security.stateful.usernamepasswordauthenticationfilter.dto.RegisterRequest;
import com.learn.spring.learnspringsecurity.security.stateful.usernamepasswordauthenticationfilter.entity.User;
import com.learn.spring.learnspringsecurity.security.stateful.usernamepasswordauthenticationfilter.enums.Role;
import com.learn.spring.learnspringsecurity.security.stateful.usernamepasswordauthenticationfilter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER) // Assign USER role by default
                .build();
        userRepository.save(user);
        return "User registered successfully!";
    }
}
