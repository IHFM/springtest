package com.example.prepost.domain.post.fasade;

import com.example.prepost.domain.post.domain.Post;
import com.example.prepost.domain.post.domain.repository.PostRepository;
import com.example.prepost.domain.post.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostFacade {
    private final PostRepository postRepository;

    public Post findByPost(Long id){
        return postRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }
}
