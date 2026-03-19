package com.security.controller;

import com.security.dto.ApiResponse;
import com.security.dto.request.LoginRequest;
import com.security.dto.request.RegistrationRequest;
import com.security.entity.UserEntity;
import com.security.service.UserService;
import com.security.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    // Register a new user and return JWT token
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> createUser(@RequestBody RegistrationRequest req) {
        try {
            UserEntity u = userService.createUser(req);
            String token = jwtUtil.generateToken(org.springframework.security.core.userdetails.User
                    .withUsername(u.getUsername())
                    .password(u.getPassword())
                    .authorities("ROLE_USER")
                    .build());
            return ResponseEntity.ok(ApiResponse.success("Registration successful", java.util.Map.of("token", token, "user", u)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Registration failed", e.getMessage()));
        }
    }

    // Login user and return JWT token if credentials are valid
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest req) {
        try {
            UserEntity u = userService.login(req);
            if (u == null) {
                return ResponseEntity.status(401).body(ApiResponse.error("Invalid credentials", "Username/email or password is incorrect"));
            }
            String token = jwtUtil.generateToken(org.springframework.security.core.userdetails.User
                    .withUsername(u.getUsername())
                    .password(u.getPassword())
                    .authorities("ROLE_USER")
                    .build());
            return ResponseEntity.ok(ApiResponse.success("Login successful", java.util.Map.of("token", token, "user", u)));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Login failed", e.getMessage()));
        }
    }
}
