package com.codex.ecomerce.services;

import com.codex.ecomerce.response.SignupRequest;

public interface AuthService {
    String createUser(SignupRequest req);
}
