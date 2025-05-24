package com.example.prepost.domain.post.service;

import com.example.prepost.domain.post.domain.Post;
import com.example.prepost.domain.post.domain.repository.PostRepository;
import com.example.prepost.domain.post.exception.PostNotFoundException;
import com.example.prepost.domain.post.fasade.PostFacade;
import com.example.prepost.domain.post.presentation.dto.response.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryPostService {
    private final PostFacade postFacade;

    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long postId){
        Post post = postFacade.findByPost(postId);
        return PostResponseDto.builder()
                .postId(postId)
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .createAt(post.getCreatedAt())
                .build();
    }
}
