package com.codex.ecomerce.services;

import com.codex.ecomerce.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findUserFromToken(String jwt) throws Exception;
    User findUserFromEmail(String email) throws Exception;
}
