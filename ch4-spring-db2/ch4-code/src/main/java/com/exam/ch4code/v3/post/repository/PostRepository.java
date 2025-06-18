package com.exam.ch4code.v3.post.repository;

import com.exam.ch4code.v3.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

  Page<Post> findByTitleContainsOrBodyContainsAllIgnoreCase(String title, String body, Pageable pageable);

  Page<Post> findByAuthor(String author, Pageable pageable);

  Page<Post> findByTitleContains(String title, Pageable pageable);

  Page<Post> findByTitleContainsAndAuthor(String title, String author, Pageable pageable);

  Page<Post> findByCreatedAfter(LocalDateTime created, Pageable pageable);

  Page<Post> findByTitleContainsAndAuthorAndCreatedAfter(String title, String author, LocalDateTime created,
                                                         Pageable page);
}
