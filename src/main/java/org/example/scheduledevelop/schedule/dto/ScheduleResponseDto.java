package org.example.scheduledevelop.schedule.dto;

import lombok.Getter;
import org.example.scheduledevelop.schedule.entity.Schedule;
import org.example.scheduledevelop.users.dto.UserInfoDto;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final UserInfoDto user;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final long commentCount;


    public ScheduleResponseDto(Long id, UserInfoDto user, String title,
                               String contents, LocalDateTime createdAt,
                               LocalDateTime updatedAt, long commentCount) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.commentCount = commentCount;
    }

    public ScheduleResponseDto(Schedule schedule, long commentCount) {
        this(schedule.getId(),
                new UserInfoDto(schedule.getUser().getId(), schedule.getUser().getUsername()),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                commentCount
        );
    }
}
