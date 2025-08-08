package org.example.scheduledevelop.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_INPUT("VAL-001", "입력값이 유효하지 않습니다"),
    DUPLICATE_USER("USR-001", "이미 가입된 사용자입니다");

    private final String code;
    private final String message;
}
