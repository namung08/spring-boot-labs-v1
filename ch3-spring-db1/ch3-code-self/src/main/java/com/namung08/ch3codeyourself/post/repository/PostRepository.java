package com.namung08.ch3codeyourself.post.repository;

import com.namung08.ch3codeyourself.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
