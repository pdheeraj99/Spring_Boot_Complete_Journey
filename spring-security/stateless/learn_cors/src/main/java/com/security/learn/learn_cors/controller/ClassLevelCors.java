package com.security.learn.learn_cors.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "*") // Allows any origin to access this controller
public class ClassLevelCors {
    // Only origins defined from CrossOrigin can access all these methods
}
