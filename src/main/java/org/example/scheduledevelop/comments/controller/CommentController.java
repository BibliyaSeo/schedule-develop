package org.example.scheduledevelop.comments.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.scheduledevelop.comments.dto.CommentRequestDto;
import org.example.scheduledevelop.comments.dto.CommentResponseDto;
import org.example.scheduledevelop.comments.service.CommentService;
import org.example.scheduledevelop.common.utils.SessionUtil;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/schedule/{scheduleId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto createComment(HttpServletRequest request, @PathVariable Long scheduleId, @Valid @RequestBody CommentRequestDto dto) {
        Long userId = SessionUtil.getLoginUserId(request);
        return commentService.createComment(scheduleId, userId, dto.getContents());
    }

    // 댓글 조회
    @GetMapping("/schedule/{scheduleId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponseDto> findComments(@PathVariable Long scheduleId) {
        return commentService.findAllComments(scheduleId);
    }

    // 댓글 수정
    @PutMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDto updateComment(HttpServletRequest request, @PathVariable Long commentId, @Valid @RequestBody CommentRequestDto dto) {
        Long userId = SessionUtil.getLoginUserId(request);
        return commentService.updateComment(commentId, userId, dto.getContents());
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(HttpServletRequest request, @PathVariable Long commentId) {
        Long userId = SessionUtil.getLoginUserId(request);
        commentService.deleteComment(commentId, userId);
    }
}
