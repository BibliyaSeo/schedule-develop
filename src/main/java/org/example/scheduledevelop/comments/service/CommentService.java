package org.example.scheduledevelop.comments.service;

import org.example.scheduledevelop.comments.dto.CommentResponseDto;

public interface CommentService {
    CommentResponseDto createComment(Long scheduleId, Long userId, String contents);
}
