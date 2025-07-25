package com.ecom.secure.api.service.impl;

import com.ecom.secure.api.dto.RegisterRequest;
import com.ecom.secure.api.entity.User;
import com.ecom.secure.api.enums.Role;
import com.ecom.secure.api.repository.UserRepository;
import com.ecom.secure.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // <-- Password hash chesi save chestunnam
                .role(Role.USER) // <-- By default, prathi user 'USER'
                .build();

        userRepository.save(user);

        return "User registered successfully!";
    }

}
