package org.spring_security.internalsunderstandingproject1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InternalsUnderstandingProject1Application {

    public static void main(String[] args) {
        System.out.println("=== STEP 1: Starting Spring Boot Application ===");
        SpringApplication.run(InternalsUnderstandingProject1Application.class, args);
        System.out.println("=== STEP 1 COMPLETE: Application Started ===");
    }

}
