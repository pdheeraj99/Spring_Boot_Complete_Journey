package com.security.learn.learn_springwithgoogleoauth.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.security.learn.learn_springwithgoogleoauth.utils.ClearCookie.clearCookie;

@RestController
public class DemoController {

    // Ee endpoint ippudu public
    @GetMapping("/")
    public String home() {
        // Try to access protected page by clicking on "PROTECTED URL 👇"
        return "<h1>Welcome Public!</h1><a href='/protected'>Login with Google</a>";
    }

    // Ee endpoint inka protected ga ne undi
    @GetMapping("/protected")
    public String protectedPage(Authentication authentication) {
        OAuth2User user = (OAuth2User) authentication.getPrincipal();
        System.out.println("User is "+ user);
        return "Hello, " + user.getAttribute("name") + "! This is a protected page only visible to authenticated person";
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        // Clear cookies
        clearCookie(response, "accessToken");
        clearCookie(response, "refreshToken");
        return ResponseEntity.ok("Logged out successfully");
    }

}
