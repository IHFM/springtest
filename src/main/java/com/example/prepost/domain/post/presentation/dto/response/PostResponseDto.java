package com.example.prepost.domain.post.presentation.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostResponseDto(Long postId, String title, String content, String author, LocalDateTime createAt) {
}
