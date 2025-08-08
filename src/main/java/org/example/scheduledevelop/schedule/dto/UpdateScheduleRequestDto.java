package org.example.scheduledevelop.schedule.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {
    Long id;
    private final String title;
    private final String contents;

    public UpdateScheduleRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
