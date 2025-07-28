package com.security.learn.learn_jwt.controller;

import com.security.learn.learn_jwt.service.CustomUserDetailsService;
import com.security.learn.learn_jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    // DTOs for request/response
    public static class AuthRequest {
        private String email;
        private String password;
        // Getters and setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class AuthResponse {
        private String token;
        public AuthResponse(String token) { this.token = token; }
        public String getToken() { return token; }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        // 1. User credentials ni authenticate cheyyadam
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        // 2. User details DB nunchi fetch cheyyadam
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());

        // 3. JWT token generate cheyyadam
        String jwtToken = jwtService.generateToken(userDetails);

        // 4. Token ni client ki return cheyyadam
        return ResponseEntity.ok(new AuthResponse(jwtToken));
    }
}
