package com.exam.ch4code.v3.post.repository;

import com.exam.ch4code.v3.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

  Page<Post> findByTitleContainsOrBodyContainsAllIgnoreCase(String title, String body, Pageable pageable);
}
