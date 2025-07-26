package com.security.learn.learn_cors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// CROSS ORIGIN RESOURCE SHARING ----------------------- CORS
// Check the config + controllers for complete CORS detailing added
//local `@CrossOrigin` annotation on a controller or method has higher priority.

@SpringBootApplication
public class LearnCorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnCorsApplication.class, args);
    }

}
