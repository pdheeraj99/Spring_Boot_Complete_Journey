package com.security.learn.learn_jwt.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    // Secret key (industry lo idi environment variable lo petali, sample ki ikkadey pettam)
    private static final String SECRET_KEY = "my-super-secret-key-for-jwt-demo-1234567890"; // [idi sample, needi vaduko]

    // SecretKey object generate cheyyadam (HS256 ki 256-bit key kavali)
    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // 1. Token generate cheyyadam (user details nunchi)
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        // Custom claims add cheyyali ante, ekkada add cheyyachu (e.g., roles)
        claims.put("roles", userDetails.getAuthorities());
        return createToken(claims, userDetails.getUsername());
    }

    // 2. Token create logic (claims, subject, expiry, sign)
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims) // claims ante payload lo pettali anukune custom data (e.g., roles)
                .subject(subject) // subject ante username/email
                .issuedAt(new Date(System.currentTimeMillis())) // token create ayye time
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiry
                .signWith(getSignInKey(), Jwts.SIG.HS256) // sign cheyyadam (HS256 algorithm)
                .compact(); // final JWT string return cheyyadam

    }

    // 3. Token nunchi username extract cheyyadam
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 4. Token nunchi custom claim extract cheyyadam (e.g., roles)
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 5. Token valid a? (signature, expiry, username match)
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // 6. Token expire ayinda check cheyyadam
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 7. Token nunchi expiry extract cheyyadam
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 8. Token nunchi anni claims extract cheyyadam
    private Claims extractAllClaims(String token) {
        // Idi JJWT 0.12.6 ki correct style
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }
}
