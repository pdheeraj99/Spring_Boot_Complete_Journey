package com.security.learn.learn_jwt2.controller.protected_endpoints.user_only_access;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserLevelController1 {

    @GetMapping("/hello-user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> sayHelloUser() {
        return ResponseEntity.ok("Hello, User! This is a very secret message.");
    }

}

