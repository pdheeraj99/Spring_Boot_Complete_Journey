package com.security.learn.session_management_filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// IMPORTANT POINTS
// 1. SessionManagementFilter can be configured for either stateful or stateless behavior, but it enforces only one policy at a time for your application.
// 2. You use it to set one rule for how your entire application should handle sessions.

@SpringBootApplication
public class SessionManagementFilterApplication {

    public static void main(String[] args) {

        SpringApplication.run(SessionManagementFilterApplication.class, args);
    }

}