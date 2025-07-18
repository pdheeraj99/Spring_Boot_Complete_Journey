package com.internalslearning.resource_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Resource Server Main Application
 * This application will:
 * 1. Start on port 8090
 * 2. Provide protected REST APIs
 * 3. Validate JWT tokens from Authorization Server
 * 4. Allow access based on token scopes
 */

@SpringBootApplication
public class ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }

}
