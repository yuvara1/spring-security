package com.security.controller;

import com.security.dto.ApiResponse;
import com.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Returns a dummy user for demonstration
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<?>> users() {
        return ResponseEntity.ok(ApiResponse.success("User fetched", Map.of("name", "yuvaraj")));
    }

    // Returns a dummy list of users for demonstration
    @GetMapping("/all-users")
    public ResponseEntity<ApiResponse<?>> allUsers() {
        return ResponseEntity.ok(ApiResponse.success("All users fetched", Map.of("name", "all-users", "username", "user", "password", "password")));
    }

    // Home endpoint for testing
    @GetMapping
    public ResponseEntity<ApiResponse<?>> home() {
        return ResponseEntity.ok(ApiResponse.success("Home endpoint", "home"));
    }
}
