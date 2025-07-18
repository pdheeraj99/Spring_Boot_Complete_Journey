package com.internalslearning.authorization_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Authorization Server Main Application
 * This application will:
 * 1. Start on port 9000
 * 2. Provide OAuth2 endpoints like /oauth2/token
 * 3. Issue JWT tokens for valid client credentials
 */

@SpringBootApplication
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

}
