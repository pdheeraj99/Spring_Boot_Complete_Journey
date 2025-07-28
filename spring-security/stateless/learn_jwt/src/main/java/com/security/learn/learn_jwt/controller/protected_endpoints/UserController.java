package com.security.learn.learn_jwt.controller.protected_endpoints;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    // Protected endpoint: Only authenticated users can access
    @GetMapping("/api/user/profile")
    public String userProfile(@AuthenticationPrincipal UserDetails userDetails) {
        return "Welcome, " + userDetails.getUsername() + "! This is your protected profile endpoint.";
    }

    // Example: Only users with ADMIN role can access
    @GetMapping("/api/admin/secret")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminSecret(@AuthenticationPrincipal UserDetails userDetails) {
        return "Hello Admin " + userDetails.getUsername() + ", this is a protected admin endpoint!";
    }
}
