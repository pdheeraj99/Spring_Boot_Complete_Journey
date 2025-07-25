package com.ecom.secure.api.service;

import com.ecom.secure.api.dto.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);
}
