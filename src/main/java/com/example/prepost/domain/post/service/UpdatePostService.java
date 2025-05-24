package com.example.prepost.domain.post.service;

import com.example.prepost.domain.post.domain.Post;
import com.example.prepost.domain.post.domain.repository.PostRepository;
import com.example.prepost.domain.post.exception.CannotModifyPostException;
import com.example.prepost.domain.post.exception.PostNotFoundException;
import com.example.prepost.domain.post.fasade.PostFacade;
import com.example.prepost.domain.post.presentation.dto.request.PostRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdatePostService {
    private final PostFacade postFacade;

    @Transactional
    public void updatePost(PostRequestDto dto, Long postId){
        Post post = postFacade.findByPost(postId);
        if(!post.getAuthor().equals(dto.getAuthor())){
            throw CannotModifyPostException.EXCEPTION;
        }
        post.update(dto.getTitle(), dto.getContent());
    }
}
//아직 User가 없기 때문에 dto로 만든 사람과 수정 사람이 같으면 업데이트 진행
//로그인 회원가입이 생기면 바뀔 예정