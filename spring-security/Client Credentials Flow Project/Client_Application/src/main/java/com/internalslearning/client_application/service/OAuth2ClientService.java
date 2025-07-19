package com.internalslearning.client_application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * OAuth2 Client Service
 *
 * This service demonstrates how to use OAuth2 client credentials
 * to call protected resources on the Resource Server.
 */
@Service
public class OAuth2ClientService {

    @Autowired
    private WebClient webClient;

    @Value("${resource-server.base-url}")
    private String resourceServerBaseUrl;

    /**
     * METHOD 1: Call Public Endpoint
     *
     * This method calls a public endpoint that doesn't require authentication.
     * No OAuth2 token is needed for this call.
     *
     * Flow: Client → Resource Server Public Endpoint → Response
     */
    public Map<String, Object> callPublicEndpoint() {
        return webClient
                .get()
                .uri(resourceServerBaseUrl + "/api/public/info")
                .retrieve()
                .bodyToMono(Map.class)
                .doOnSuccess(response ->
                        System.out.println("✅ Public endpoint called successfully: " + response))
                .doOnError(error ->
                        System.err.println("❌ Public endpoint call failed: " + error.getMessage()))
                .block();
    }

    /**
     * METHOD 2: Call Protected Endpoint (Read Scope)
     *
     * This method calls a protected endpoint that requires 'read' scope.
     * The WebClient automatically obtains and includes the OAuth2 token.
     *
     * Flow: Client → Get Token → Add to Request → Resource Server → Validate Token → Response
     */
    public List<Map<String, Object>> getUsers() {
        return webClient
                .get()
                .uri(resourceServerBaseUrl + "/api/users")
                .retrieve()
                .bodyToMono(List.class)
                .doOnSuccess(response ->
                        System.out.println("✅ Users endpoint called successfully. Found " +
                                ((List<?>) response).size() + " users"))
                .doOnError(error ->
                        System.err.println("❌ Users endpoint call failed: " + error.getMessage()))
                .block();
    }

    /**
     * METHOD 3: Call Admin Endpoint (Write Scope)
     *
     * This method calls an admin endpoint that requires 'write' scope.
     * Demonstrates access to higher-privilege resources.
     *
     * Flow: Client → Get Token with Write Scope → Resource Server → Validate Scope → Response
     */
    public Map<String, Object> getAdminUsers() {
        return webClient
                .get()
                .uri(resourceServerBaseUrl + "/api/admin/users")
                .retrieve()
                .bodyToMono(Map.class)
                .doOnSuccess(response ->
                        System.out.println("✅ Admin endpoint called successfully: " + response))
                .doOnError(error ->
                        System.err.println("❌ Admin endpoint call failed: " + error.getMessage()))
                .block();
    }

    /**
     * METHOD 4: Get Token Information
     *
     * This method calls an endpoint that returns information about the JWT token.
     * Useful for debugging and understanding token structure.
     */
    public Map<String, Object> getTokenInfo() {
        return webClient
                .get()
                .uri(resourceServerBaseUrl + "/api/token-info")
                .retrieve()
                .bodyToMono(Map.class)
                .doOnSuccess(response ->
                        System.out.println("✅ Token info retrieved: " + response))
                .doOnError(error ->
                        System.err.println("❌ Token info call failed: " + error.getMessage()))
                .block();
    }
}
