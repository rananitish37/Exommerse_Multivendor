package com.codex.ecomerce.services.impl;

import com.codex.ecomerce.config.JwtProvider;

import com.codex.ecomerce.model.User;
import com.codex.ecomerce.repository.UserRepository;
import com.codex.ecomerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    public User findUserFromToken(String jwt) throws Exception {
        String email= jwtProvider.getEmailFromJwtToken(jwt);
        return this.findUserFromEmail(email);
    }

    @Override
    public User findUserFromEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new Exception("User not found");
        }
        return user;
    }
}
