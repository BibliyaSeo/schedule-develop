package org.example.scheduledevelop.users.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.scheduledevelop.users.dto.SingUpUserRequestDto;
import org.example.scheduledevelop.users.dto.UpdatePasswordRequestDto;
import org.example.scheduledevelop.users.dto.UserResponseDto;
import org.example.scheduledevelop.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto signUp(@Valid @RequestBody SingUpUserRequestDto dto) {
        return userService.signUp(dto.getUsername(), dto.getEmail(), dto.getPassword());
    }

    // 전체 유저 조회(또는 유저명 조회)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> findUserByUsername(@RequestParam(required = false) String username) {
        return userService.findUserByUsername(username);
    }

    // 유저 단건 조회
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    // 비밀번호 변경
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Long id, @Valid @RequestBody UpdatePasswordRequestDto dto) {
        userService.updatePassword(id, dto.getOldPassword(), dto.getNewPassword());
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
