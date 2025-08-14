package org.example.scheduledevelop.comments.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduledevelop.comments.dto.CommentResponseDto;
import org.example.scheduledevelop.comments.entity.Comment;
import org.example.scheduledevelop.comments.repository.CommentRepository;
import org.example.scheduledevelop.schedule.entity.Schedule;
import org.example.scheduledevelop.schedule.service.ScheduleService;
import org.example.scheduledevelop.users.entity.User;
import org.example.scheduledevelop.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {
    private final ScheduleService scheduleService;
    private final UserService userService;
    private final CommentRepository commentRepository;

    @Override
    public CommentResponseDto createComment(Long scheduleId, Long userId, String contents) {
        // 스케줄 엔티티 조회
        Schedule schedule = scheduleService.findEntityById(scheduleId);
        // 유저 엔티티 조회
        User user = userService.findEntityById(userId);
        // 댓글 엔티티 생성
        Comment comment = new Comment(contents, user, schedule);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAllComments(Long scheduleId) {
        // 스케줄 유효
        Schedule schedule = scheduleService.findEntityById(scheduleId);
        return commentRepository.findAllByScheduleIdOrderByCreatedAtDesc(schedule.getId())
                .stream()
                .map(CommentResponseDto::new)
                .toList();
    }

    @Override
    public CommentResponseDto updateComment(Long commentId, Long userId, String contents) {
        Comment findComment = commentRepository.findByIdOrElseThrow(commentId);
        // 댓글의 작성자 아이디와 지금 로그인 된 아이디가 같다면
        if (!findComment.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정 권한이 없습니다.");
        }
        findComment.updateComment(contents);
        return new CommentResponseDto(findComment);
    }

    @Override
    public void deleteComment(Long commentId, Long userId) {
        Comment findComment = commentRepository.findByIdOrElseThrow(commentId);
        if (!findComment.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "삭제 권한이 없습니다.");
        }
        commentRepository.delete(findComment);
    }
}
