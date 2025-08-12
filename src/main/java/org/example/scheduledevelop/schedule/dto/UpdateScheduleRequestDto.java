package org.example.scheduledevelop.schedule.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {
    @Size(max = 10, message = "제목은 10자 이내여야 합니다.")
    private final String title;

    @Size(max = 100, message = "내용은 10자 이내여야 합니다.")
    private final String contents;

    public UpdateScheduleRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
