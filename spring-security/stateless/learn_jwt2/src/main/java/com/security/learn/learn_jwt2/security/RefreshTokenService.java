package com.security.learn.learn_jwt2.security;

import com.security.learn.learn_jwt2.entity.RefreshToken;
import com.security.learn.learn_jwt2.entity.User;
import com.security.learn.learn_jwt2.exception.RefreshTokenException;
import com.security.learn.learn_jwt2.repository.RefreshTokenRepository;
import com.security.learn.learn_jwt2.repository.UserRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshTokenExpiration;

    public RefreshToken createRefreshToken(String username) {
        User user = userRepository.findByEmail(username).orElseThrow();

        // Check if a token already exists for this user and delete it
        refreshTokenRepository.findByUser(user).ifPresent(refreshTokenRepository::delete);

        RefreshToken newRefreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(refreshTokenExpiration))
                .build();
        return refreshTokenRepository.save(newRefreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            // throw new RuntimeException(token.getToken() + " Refresh token was expired. Please make a new signin request");

            // Throw our new custom exception
            throw new RefreshTokenException(token.getToken(), "Refresh token was expired. Please make a new sign-in request.");
        }
        return token;
    }
}
