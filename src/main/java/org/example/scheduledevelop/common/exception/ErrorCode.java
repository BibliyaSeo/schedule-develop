package org.example.scheduledevelop.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_INPUT("VAL-001", "입력값이 유효하지 않습니다"),
    DUPLICATE_USER("USR-001", "이미 가입된 사용자입니다"),
    USER_NOT_FOUND("USR-002", "존재하지 않는 회원입니다"),
    POST_NOT_FOUND("POST-001", "존재하지 않는 게시글입니다"),
    COMMENT_NOT_FOUND("COMMENT-001", "존재하지 않는 댓글입니다");

    private final String code;
    private final String message;
}
