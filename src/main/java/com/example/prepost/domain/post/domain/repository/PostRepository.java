package com.example.prepost.domain.post.domain.repository;

import com.example.prepost.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
