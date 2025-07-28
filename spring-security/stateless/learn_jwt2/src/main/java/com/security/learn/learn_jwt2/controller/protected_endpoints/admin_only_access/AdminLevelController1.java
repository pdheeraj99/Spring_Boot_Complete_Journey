package com.security.learn.learn_jwt2.controller.protected_endpoints.admin_only_access;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminLevelController1 {

    @GetMapping("/hello-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> sayHelloAdmin() {
        return ResponseEntity.ok("Hello, ADMIN! This is a very secret message.");
    }

}
