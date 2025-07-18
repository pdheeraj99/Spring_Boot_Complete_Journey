package com.internalslearning.resource_server.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Protected Resource Controller
 *
 * This controller provides various endpoints that demonstrate different
 * security configurations and scope requirements.
 */
@RestController
@RequestMapping("/api")
public class ResourceController {

    /**
     * ENDPOINT 1: Public Endpoint
     *
     * This endpoint is accessible without any authentication.
     * It's configured as public in the security configuration.
     *
     * Access: Anyone can call this endpoint
     */
    @GetMapping("/public/info")
    public Map<String, Object> publicInfo() {
        return Map.of(
                "message", "This is a public endpoint",
                "timestamp", System.currentTimeMillis(),
                "server", "Resource Server"
        );
    }

    /**
     * ENDPOINT 2: Read-Only Endpoint
     *
     * This endpoint requires the 'read' scope in the JWT token.
     * The JWT is automatically validated by Spring Security.
     *
     * Access: Clients with 'read' scope can call this endpoint
     *
     * Flow: Client Request → JWT Validation → Scope Check → Data Return
     */
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public List<Map<String, Object>> getUsers(@AuthenticationPrincipal Jwt jwt) {
        return Arrays.asList(
                Map.of(
                        "id", 1,
                        "name", "John Doe",
                        "email", "john@example.com",
                        "department", "Engineering"
                ),
                Map.of(
                        "id", 2,
                        "name", "Jane Smith",
                        "email", "jane@example.com",
                        "department", "Marketing"
                ),
                Map.of(
                        "id", 3,
                        "name", "Bob Johnson",
                        "email", "bob@example.com",
                        "department", "Sales"
                )
        );
    }

    /**
     * ENDPOINT 3: Admin-Only Endpoint
     *
     * This endpoint requires the 'write' scope in the JWT token.
     * It also demonstrates how to access JWT claims.
     *
     * Access: Clients with 'write' scope can call this endpoint
     */
    @GetMapping("/admin/users")
    @PreAuthorize("hasAuthority('SCOPE_write')")
    public Map<String, Object> getAdminUsers(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
                "users", getUsers(jwt),
                "total", 3,
                "requestedBy", jwt.getSubject(), // Client ID from JWT
                "scopes", jwt.getClaimAsStringList("scope"),
                "tokenIssuedAt", jwt.getIssuedAt(),
                "tokenExpiresAt", jwt.getExpiresAt()
        );
    }

    /**
     * ENDPOINT 4: Token Information Endpoint
     *
     * This endpoint returns information about the JWT token itself.
     * Useful for debugging and understanding token structure.
     */
    @GetMapping("/token-info")
    public Map<String, Object> tokenInfo(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
                "subject", jwt.getSubject(), // Usually the client ID
                "issuer", jwt.getIssuer(), // Authorization server URL
                "audience", jwt.getAudience(), // Intended audience
                "scopes", jwt.getClaimAsStringList("scope"),
                "issuedAt", jwt.getIssuedAt(),
                "expiresAt", jwt.getExpiresAt(),
                "tokenId", jwt.getId(),
                "allClaims", jwt.getClaims()
        );
    }
}
