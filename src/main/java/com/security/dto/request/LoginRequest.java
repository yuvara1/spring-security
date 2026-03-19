package com.security.dto.request;

import lombok.Data;

/**
 * A DTO for transferring login request data.
 */
@Data
public class LoginRequest {
    private String usernameOrEmail;
    private String password;

}
