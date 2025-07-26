package com.learn.spring.learnspringsecurity.security.stateful.usernamepasswordauthenticationfilter.controller;

import com.learn.spring.learnspringsecurity.security.stateful.usernamepasswordauthenticationfilter.dto.RegisterRequest;
import com.learn.spring.learnspringsecurity.security.stateful.usernamepasswordauthenticationfilter.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
