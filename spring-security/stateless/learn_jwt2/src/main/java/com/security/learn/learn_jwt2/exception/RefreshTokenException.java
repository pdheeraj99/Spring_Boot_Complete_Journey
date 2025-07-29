package com.security.learn.learn_jwt2.exception;

// CUSTOM EXCEPTION
public class RefreshTokenException extends RuntimeException {
    public RefreshTokenException(String token, String message) {
        super(String.format("Failed for token [%s]: %s", token, message));
    }
}