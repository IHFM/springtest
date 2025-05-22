package com.example.prepost.domain.post.presentation;

import com.example.prepost.domain.post.presentation.dto.request.PostRequestDto;
import com.example.prepost.domain.post.presentation.dto.response.PostResponseDto;
import com.example.prepost.domain.post.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final CreatePostService createPostService;
    private final QueryPostService queryPostService;
    private final QueryPostListService queryPostListService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody @Valid PostRequestDto dto){
        createPostService.createPost(dto);
    }

    @GetMapping("/{post-id}")
    public PostResponseDto getPost(@PathVariable("post-id") Long postId){
        return queryPostService.getPost(postId);
    }

    @GetMapping
    public List<PostResponseDto> getPostList(){
        return queryPostListService.getPostList();
    }

    @PatchMapping("/{post-id}")
    public void updatePost(@RequestBody @Valid PostRequestDto dto, @PathVariable("post-id") Long postId){
        updatePostService.updatePost(dto, postId);
    }

    @DeleteMapping("/{post-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("post-id") Long postId){
        deletePostService.deletePost(postId);
    }

}
