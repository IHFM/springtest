package com.example.prepost.domain.post.presentation;

import com.example.prepost.domain.post.presentation.dto.request.PostRequestDto;
import com.example.prepost.domain.post.presentation.dto.response.PostResponseDto;
import com.example.prepost.domain.post.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


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
    public Long createPost(@RequestBody @Valid PostRequestDto dto){
        return createPostService.createPost(dto);
    }

    @GetMapping("/{post-id}")
    public PostResponseDto getPost(@PathVariable("post-id") Long postId){
        return queryPostService.getPost(postId);
    }

    @GetMapping
    public Page<PostResponseDto> getPostList(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable){
        return queryPostListService.getPostList(pageable);
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
