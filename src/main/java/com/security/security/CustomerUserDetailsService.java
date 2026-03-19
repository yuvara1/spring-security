package com.security.security;

import com.security.entity.UserEntity;
import com.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load user from database by username
        UserEntity userEntity = userRepository.findByUsername(username);
        System.out.println("user: " + userEntity);
        // Return Spring Security User with ROLE_USER authority
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.singleton(() -> "ROLE_USER"));
    }
}
