package com.codex.ecomerce.services;

import com.codex.ecomerce.domain.USER_ROLE;
import com.codex.ecomerce.request.LoginRequest;
import com.codex.ecomerce.response.AuthResponse;
import com.codex.ecomerce.response.SignupRequest;

public interface AuthService {
    void sentLoginOtp(String email, USER_ROLE role) throws Exception;
    String createUser(SignupRequest req) throws Exception;
    AuthResponse siging(LoginRequest req) throws Exception;
}
