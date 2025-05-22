package com.example.prepost.domain.post.service;

import com.example.prepost.domain.post.domain.repository.PostRepository;
import com.example.prepost.domain.post.presentation.dto.response.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryPostListService {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostList(){
        return postRepository.findAll().stream()
                .map(post -> PostResponseDto.builder()
                        .postId(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .author(post.getAuthor())
                        .createAt(post.getCreatedAt())
                        .build())
                .toList();
    }
}
