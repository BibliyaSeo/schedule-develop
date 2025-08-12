package org.example.scheduledevelop.comments.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.scheduledevelop.comments.dto.CommentResponseDto;
import org.example.scheduledevelop.comments.dto.CreateCommentRequestDto;
import org.example.scheduledevelop.comments.service.CommentService;
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
    public CommentResponseDto createComment(HttpServletRequest request, @PathVariable Long scheduleId, @Valid @RequestBody CreateCommentRequestDto dto) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("LOGIN_USER");
        return commentService.createComment(scheduleId, userId, dto.getContents());
    }

    // 댓글 조회
    @GetMapping("/schedule/{scheduleId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponseDto> findComments(@PathVariable Long scheduleId) {
        return commentService.findAllComments(scheduleId);
    }
}
