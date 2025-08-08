package org.example.scheduledevelop.users.service;

import org.example.scheduledevelop.users.dto.UserResponseDto;

public interface UserService {
    UserResponseDto signUp(String username, String email, String password);
}
