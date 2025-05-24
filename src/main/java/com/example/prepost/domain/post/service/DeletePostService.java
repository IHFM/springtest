package com.example.prepost.domain.post.service;

import com.example.prepost.domain.post.domain.Post;
import com.example.prepost.domain.post.domain.repository.PostRepository;
import com.example.prepost.domain.post.exception.PostNotFoundException;
import com.example.prepost.domain.post.fasade.PostFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePostService {
    private final PostRepository postRepository;
    private final PostFacade postFacade;

    @Transactional
    public void deletePost(Long postId){
        Post post = postFacade.findByPost(postId);
        postRepository.delete(post);
    }
}
//아직 User가 없기 때문에 그냥 삭제
//추후 로그인 회원가입이 생기면 바뀔 예정