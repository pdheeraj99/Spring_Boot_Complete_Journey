package com.learn.spring.learnspringsecurity.security.stateful.usernamepasswordauthenticationfilter.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
