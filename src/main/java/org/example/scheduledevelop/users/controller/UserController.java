package org.example.scheduledevelop.users.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduledevelop.users.dto.SingUpUserRequestDto;
import org.example.scheduledevelop.users.dto.UserResponseDto;
import org.example.scheduledevelop.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto signUp(@RequestBody SingUpUserRequestDto dto) {
        return userService.signUp(dto.getUsername(), dto.getEmail(), dto.getPassword());
    }
}
