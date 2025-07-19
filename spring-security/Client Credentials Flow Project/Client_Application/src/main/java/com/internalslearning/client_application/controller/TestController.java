package com.internalslearning.client_application.controller;

import com.internalslearning.client_application.service.OAuth2ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Test Controller
 *
 * This controller provides endpoints to test the OAuth2 client functionality.
 * Each endpoint demonstrates different aspects of the Client Credentials flow.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private OAuth2ClientService clientService;

    /**
     * Test public endpoint access
     */
    @GetMapping("/public")
    public Map<String, Object> testPublicEndpoint() {
        System.out.println("🔄 Testing public endpoint access...");
        return clientService.callPublicEndpoint();
    }

    /**
     * Test protected endpoint access with read scope
     */
    @GetMapping("/users")
    public List<Map<String, Object>> testUsersEndpoint() {
        System.out.println("🔄 Testing users endpoint access (read scope required)...");
        return clientService.getUsers();
    }

    /**
     * Test admin endpoint access with write scope
     */
    @GetMapping("/admin")
    public Map<String, Object> testAdminEndpoint() {
        System.out.println("🔄 Testing admin endpoint access (write scope required)...");
        return clientService.getAdminUsers();
    }

    /**
     * Test token information endpoint
     */
    @GetMapping("/token-info")
    public Map<String, Object> testTokenInfo() {
        System.out.println("🔄 Testing token info endpoint...");
        return clientService.getTokenInfo();
    }

    /**
     * Test all endpoints in sequence
     */
    @GetMapping("/all")
    public Map<String, Object> testAllEndpoints() {
        System.out.println("🔄 Testing all endpoints...");

        try {
            Map<String, Object> publicResult = clientService.callPublicEndpoint();
            List<Map<String, Object>> usersResult = clientService.getUsers();
            Map<String, Object> adminResult = clientService.getAdminUsers();
            Map<String, Object> tokenInfo = clientService.getTokenInfo();

            return Map.of(
                    "public", publicResult,
                    "users", usersResult,
                    "admin", adminResult,
                    "tokenInfo", tokenInfo,
                    "status", "All tests completed successfully! 🎉"
            );
        } catch (Exception e) {
            return Map.of(
                    "error", "Test failed: " + e.getMessage(),
                    "status", "Test failed ❌"
            );
        }
    }
}
