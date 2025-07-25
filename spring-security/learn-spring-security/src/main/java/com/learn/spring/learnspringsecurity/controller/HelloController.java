package com.learn.spring.learnspringsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/details")
    public ResponseEntity<String> getAllDetails() {
        return ResponseEntity.ok().body("Hello");
    }


}
