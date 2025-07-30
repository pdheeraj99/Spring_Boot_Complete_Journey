package com.security.learn.learn_springwithgoogleoauth.security;

import com.security.learn.learn_springwithgoogleoauth.entity.RefreshToken;
import com.security.learn.learn_springwithgoogleoauth.entity.User;
import com.security.learn.learn_springwithgoogleoauth.exception.RefreshTokenException;
import com.security.learn.learn_springwithgoogleoauth.repository.RefreshTokenRepository;
import com.security.learn.learn_springwithgoogleoauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

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

    public void verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            // We will no longer delete the token here. We just throw the exception.
            // refreshTokenRepository.delete(token);

            // This exception will travel top
            throw new RefreshTokenException(token.getToken(), "Refresh token was expired. Please make a new sign-in request.");
        }
    }
}
