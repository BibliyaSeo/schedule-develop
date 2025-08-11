package org.example.scheduledevelop.users.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.scheduledevelop.users.auth.dto.LogInRequestDto;
import org.example.scheduledevelop.users.auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 로그인
    @PostMapping("/login")
    public String login(HttpServletRequest request, @Valid @RequestBody LogInRequestDto dto) {
        Long userId = authService.login(dto.getEmail(), dto.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute("LOGIN_USER", userId);

        return "로그인 성공";
    }

    // 로그아웃
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        // 세션이 존재하면 -> 로그인이 된 경우
        if (session != null) {
            session.invalidate(); // 해당 세션(데이터)을 삭제한다.
        }
    }
}
