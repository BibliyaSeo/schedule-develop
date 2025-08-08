package org.example.scheduledevelop.users.service;

import org.example.scheduledevelop.users.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto signUp(String username, String email, String password);

    List<UserResponseDto> findUserByUsername(String username);

    UserResponseDto findUserById(Long id);

    void updatePassword(Long id, String oldPassword, String newPassword);
}
