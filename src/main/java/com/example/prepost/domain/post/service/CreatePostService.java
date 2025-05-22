package com.example.prepost.domain.post.service;

import com.example.prepost.domain.post.domain.Post;
import com.example.prepost.domain.post.domain.repository.PostRepository;
import com.example.prepost.domain.post.presentation.dto.request.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePostService {
    private final PostRepository postRepository;

    @Transactional
    public void createPost(PostRequestDto dto){
        Post post = Post.create(dto.getTitle(), dto.getContent(), dto.getAuthor());
        postRepository.save(post);
    }
}
