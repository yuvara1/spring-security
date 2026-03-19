package com.security.dto.request;

import lombok.Data;

// DTO for registration request
@Data
public class RegistrationRequest {
    private String username;
    private String email;
    private String password;
}
