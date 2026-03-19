package com.security.service;

import com.security.dto.request.LoginRequest;
import com.security.dto.request.RegistrationRequest;
import com.security.entity.UserEntity;
import com.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserEntity createUser(RegistrationRequest user) {
        // Extract user details from registration request
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        // Build and save new user entity with encoded password
        UserEntity savedUserEntity = UserEntity.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
        return userRepository.save(savedUserEntity);
    }

    public UserEntity login(LoginRequest loginRequest) {
        // Extract login credentials
        String usernameOrEmail = loginRequest.getUsernameOrEmail();
        String password = loginRequest.getPassword();

        // Find user by username or email
        UserEntity userEntity = userRepository.findByUsernameOrEmail(usernameOrEmail);
        System.out.println("user: " + userEntity);
        // Validate password
        if (userEntity != null && passwordEncoder.matches(password, userEntity.getPassword())) {
            return userEntity;
        }
        return null; // or throw an exception for invalid credentials
    }

}
