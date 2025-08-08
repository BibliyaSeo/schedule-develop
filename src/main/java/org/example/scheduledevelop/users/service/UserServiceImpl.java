package org.example.scheduledevelop.users.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduledevelop.common.exception.ErrorCode;
import org.example.scheduledevelop.common.exception.MyCustomException;
import org.example.scheduledevelop.users.dto.UserResponseDto;
import org.example.scheduledevelop.users.entity.User;
import org.example.scheduledevelop.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // 회원가입
    @Override
    public UserResponseDto signUp(String username, String email, String password) {
        if (username == null || email == null || password == null) {
            throw new MyCustomException(ErrorCode.INVALID_INPUT);
        }
        User newUser = userRepository.save(new User(username, email, password));
        return new UserResponseDto(newUser);
    }
}
