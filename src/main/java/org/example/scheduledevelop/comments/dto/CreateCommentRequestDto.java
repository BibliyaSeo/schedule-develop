package org.example.scheduledevelop.comments.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequestDto {
    @NotBlank(message = "내용은 필수 입력 사항입니다.")
    @Size(max = 50, message = "내용은 50자 이내여야 합니다.")
    private final String contents;

    public CreateCommentRequestDto(String contents) {
        this.contents = contents;
    }
}
