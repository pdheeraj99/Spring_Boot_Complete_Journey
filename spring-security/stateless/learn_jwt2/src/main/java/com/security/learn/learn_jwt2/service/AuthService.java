package com.security.learn.learn_jwt2.service;

import com.security.learn.learn_jwt2.dto.AuthResponse;
import com.security.learn.learn_jwt2.dto.LoginRequest;
import com.security.learn.learn_jwt2.dto.RefreshTokenRequest;
import com.security.learn.learn_jwt2.dto.RegisterRequest;
import com.security.learn.learn_jwt2.entity.RefreshToken;
import com.security.learn.learn_jwt2.enums.Role;
import com.security.learn.learn_jwt2.exception.RefreshTokenException;
import com.security.learn.learn_jwt2.repository.RefreshTokenRepository;
import com.security.learn.learn_jwt2.repository.UserRepository;
import com.security.learn.learn_jwt2.security.JwtService;
import com.security.learn.learn_jwt2.security.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import com.security.learn.learn_jwt2.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;

    public String register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Password ni hash chesi save chestunnam
                .role(Role.USER) // Default ga prathi user ki 'USER' role istunnam
                .build();
        userRepository.save(user);
        return "User registered successfully!";
    }

    // --- ADD THIS NEW METHOD ---
    public AuthResponse login(LoginRequest request) {
        // First, we authenticate the user. If credentials are wrong, it will throw an exception.
        // Take these credentials, run them through the full security verification process, and tell me if this user is legitimate or not.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // If authentication is successful, we find the user and generate a JWT.
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(); // We know the user exists at this point.
        var jwtToken = jwtService.generateToken(user);

        var refreshToken = refreshTokenService.createRefreshToken(request.getEmail());

        return AuthResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken.getToken())
                .build();
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        // 1. Find the old refresh token in the database
        RefreshToken oldRefreshToken = refreshTokenService.findByToken(request.getToken())
                .orElseThrow(() -> new RefreshTokenException(request.getToken(), "Refresh token not found!"));

        // 2. Verify if the old token has expired
        refreshTokenService.verifyExpiration(oldRefreshToken);

        // 3. Get the user associated with the token
        User user = oldRefreshToken.getUser();

        // 4. IMPORTANT: Delete the old refresh token
        refreshTokenRepository.delete(oldRefreshToken);

        // 5. Generate a new access token
        String newAccessToken = jwtService.generateToken(user);

        // 6. Generate a new refresh token
        RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(user.getUsername());

        // 7. Return both new tokens to the user
        return AuthResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken.getToken())
                .build();
    }

    public String logout(RefreshTokenRequest request) {
        // Find the refresh token in the database
        refreshTokenRepository.findByToken(request.getToken())
                // If it exists, delete it
                .ifPresent(refreshTokenRepository::delete);

        return "Logout successful!";
    }

    public String registerAdmin(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN) // <-- The only difference is here
                .build();
        userRepository.save(user);
        return "Admin registered successfully!";
    }
}
