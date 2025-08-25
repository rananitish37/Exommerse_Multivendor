package com.codex.ecomerce.controller;

import com.codex.ecomerce.model.User;
import com.codex.ecomerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users/profile")
    public ResponseEntity<User> getUsers(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserFromToken(jwt);
        return ResponseEntity.ok(user);
    }
}
