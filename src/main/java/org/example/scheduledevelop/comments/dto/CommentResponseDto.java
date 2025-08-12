package org.example.scheduledevelop.comments.dto;

import lombok.Getter;
import org.example.scheduledevelop.comments.entity.Comment;
import org.example.scheduledevelop.users.dto.UserInfoDto;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final UserInfoDto user;
    private final Long scheduleId;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CommentResponseDto(Long id, UserInfoDto user, Long scheduleId, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.scheduleId = scheduleId;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CommentResponseDto(Comment comment) {
        this(comment.getId(),
                new UserInfoDto(comment.getUser().getId(), comment.getUser().getUsername()),
                comment.getSchedule().getId(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
