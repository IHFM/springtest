package com.example.prepost.domain.post.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequestDto {
    @NotBlank(message = "제목을 입력해주세요")
    @Size(max = 30, message = "제목은 30자 이내로 입력해주세요")
    private String title;

    @NotBlank(message = "본문을 입력해주세요")
    private String content;

    @NotBlank(message = "이름을 입력해주세요")
    @Size(max = 30, message = "이름은 30자 이내로 입력해주세요")
    private String author;
}
