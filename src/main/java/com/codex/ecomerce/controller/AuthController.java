package com.codex.ecomerce.controller;


import com.codex.ecomerce.domain.USER_ROLE;
import com.codex.ecomerce.model.User;
import com.codex.ecomerce.repository.UserRepository;
import com.codex.ecomerce.response.AuthResponse;
import com.codex.ecomerce.response.SignupRequest;
import com.codex.ecomerce.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public AuthController(){
        System.out.println("AuthController loaded");
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody @Valid SignupRequest req) {
        System.out.println("Signup request: " + req);
        String jwt = authService.createUser(req);
        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setMessage("register success");
        response.setRole(USER_ROLE.ROLE_CUSTOMER);
        System.out.println("Signup response: " + response);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/getUsers")  // better plural naming
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
