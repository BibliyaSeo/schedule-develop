package org.example.scheduledevelop.users.service;

import org.example.scheduledevelop.users.dto.UserResponseDto;
import org.example.scheduledevelop.users.entity.User;

import java.util.List;

public interface UserService {
    UserResponseDto signUp(String username, String email, String password);

    List<UserResponseDto> findUserByUsername(String username);

    UserResponseDto findUserById(Long id);

    User findEntityById(Long id);

    void updatePassword(Long id, String oldPassword, String newPassword);

    void deleteUser(Long id);
}
