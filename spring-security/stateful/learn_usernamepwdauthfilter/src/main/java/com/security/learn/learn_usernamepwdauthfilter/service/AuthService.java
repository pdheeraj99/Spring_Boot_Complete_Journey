package com.security.learn.learn_usernamepwdauthfilter.service;

import com.security.learn.learn_usernamepwdauthfilter.dto.RegisterRequest;
import com.security.learn.learn_usernamepwdauthfilter.entity.User;
import com.security.learn.learn_usernamepwdauthfilter.enums.Role;
import com.security.learn.learn_usernamepwdauthfilter.repository.UserRepository;
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
