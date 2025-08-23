package com.codex.ecomerce.services;

import com.codex.ecomerce.request.LoginRequest;
import com.codex.ecomerce.response.AuthResponse;
import com.codex.ecomerce.response.SignupRequest;

public interface AuthService {
    void sentLoginOtp(String email) throws Exception;
    String createUser(SignupRequest req) throws Exception;
    AuthResponse siging(LoginRequest req) throws Exception;
}
