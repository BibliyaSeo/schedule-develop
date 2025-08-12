package org.example.scheduledevelop.schedule.dto;

import lombok.Getter;
import org.example.scheduledevelop.comments.dto.CommentResponseDto;
import org.example.scheduledevelop.schedule.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class DetailScheduleResponseDto {
    private final Long id;
    private final Long userId;
    private final String username;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private final List<CommentResponseDto> comments;

    public DetailScheduleResponseDto(Long id, Long userId, String username, String title, String contents,
                                     LocalDateTime createdAt, LocalDateTime updatedAt,
                                     List<CommentResponseDto> comments) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.comments = comments;
    }

    public DetailScheduleResponseDto(Schedule schedule, List<CommentResponseDto> comments) {
        this(schedule.getId(),
                schedule.getUser().getId(),
                schedule.getUser().getUsername(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                comments
        );
    }
}
