package org.example.scheduledevelop.users.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduledevelop.common.exception.ErrorCode;
import org.example.scheduledevelop.common.exception.MyCustomException;
import org.example.scheduledevelop.users.config.PasswordEncoder;
import org.example.scheduledevelop.users.dto.UserResponseDto;
import org.example.scheduledevelop.users.entity.User;
import org.example.scheduledevelop.users.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    public UserResponseDto signUp(String username, String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new MyCustomException(ErrorCode.DUPLICATE_USER);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User newUser = userRepository.save(new User(username, email, encodedPassword));
        return new UserResponseDto(newUser);
    }

    // 전체 유저 조회(또는 유저명 조회)
    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> findUserByUsername(String username) {
        List<User> users;
        if (username == null || username.isEmpty()) {
            users = userRepository.findAll(Sort.by(Sort.Direction.ASC, "createdAt"));
        } else {
            users = userRepository.findByUsernameOrderByCreatedAtAsc(username);
        }
        return users.stream().map(UserResponseDto::new).toList();
    }

    // 유저 단건 조회
    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findUserById(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        return new UserResponseDto(findUser);
    }

    // 엔티티 조회용
    @Override
    public User findEntityById(Long id) {
        return userRepository.findByIdOrElseThrow(id);
    }

    // 비밀번호 변경
    @Override
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        if (!passwordEncoder.matches(oldPassword, findUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
        }

        String encodedNewPassword = passwordEncoder.encode(newPassword);
        findUser.updatePassword(encodedNewPassword);
    }

    @Override
    public void deleteUser(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        userRepository.delete(findUser);
    }
}
