package com.exam.ch4code.v2.post.repository;

import com.exam.ch4code.v2.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

  Page<Post> findByTitleContainsOrBodyContainsAllIgnoreCase(String title, String body, Pageable pageable);
}
