package org.example.scheduledevelop.comments.repository;

import org.example.scheduledevelop.comments.entity.Comment;
import org.example.scheduledevelop.common.exception.ErrorCode;
import org.example.scheduledevelop.common.exception.MyCustomException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByScheduleIdOrderByCreatedAtDesc(Long scheduleId);

    default Comment findByIdOrElseThrow(Long commentId) {
        return findById(commentId).orElseThrow(() -> new MyCustomException(ErrorCode.COMMENT_NOT_FOUND));
    }
}
