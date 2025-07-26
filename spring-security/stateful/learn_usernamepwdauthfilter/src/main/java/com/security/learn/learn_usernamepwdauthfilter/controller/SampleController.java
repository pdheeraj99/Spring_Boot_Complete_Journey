package com.security.learn.learn_usernamepwdauthfilter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/details")
    public ResponseEntity<String> getAllDetails() {
        return ResponseEntity.ok().body("Hello");
    }

}
