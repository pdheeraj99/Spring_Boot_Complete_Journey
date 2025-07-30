package com.security.learn.learn_springwithgoogleoauth.exception;

// CUSTOM EXCEPTION
public class RefreshTokenException extends RuntimeException {
    public RefreshTokenException(String token, String message) {
        super(String.format("Failed for token [%s]: %s", token, message));
    }
}