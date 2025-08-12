package org.example.scheduledevelop.comments.service;

import org.example.scheduledevelop.comments.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto createComment(Long scheduleId, Long userId, String contents);

    List<CommentResponseDto> findAllComments(Long scheduleId);
}
