package org.spring_security.internalsunderstandingproject1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController1 {
    @GetMapping("/public")
    public String publicEndpoint() {
        return "Public endpoint!";
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        return "Private endpoint!";
    }
}