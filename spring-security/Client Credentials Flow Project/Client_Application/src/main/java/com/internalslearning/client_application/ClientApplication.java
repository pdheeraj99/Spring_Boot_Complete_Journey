package com.internalslearning.client_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Client Application Main Class

 * This application will:
 * 1. Start on port 8080
 * 2. Obtain access tokens from Authorization Server
 * 3. Call protected APIs on Resource Server
 * 4. Demonstrate token usage and refresh
 */

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
