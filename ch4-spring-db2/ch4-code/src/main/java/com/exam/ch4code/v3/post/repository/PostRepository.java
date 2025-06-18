package com.exam.ch4code.v3.post.repository;

import com.exam.ch4code.v3.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface PostRepository extends JpaRepository<Post, Long> {

  Page<Post> findByTitleContainsOrBodyContainsAllIgnoreCase(String title, String body, Pageable pageable);

  Page<Post> findByAuthor(String author, Pageable pageable);

  Page<Post> findByTitleContains(String title, Pageable pageable);

  Page<Post> findByTitleContainsAndAuthor(String title, String author, Pageable pageable);

  Page<Post> findByCreatedAfter(LocalDateTime created, Pageable pageable);

  Page<Post> findByTitleContainsAndAuthorAndCreatedAfter(String title, String author, LocalDateTime created,
                                                         Pageable page);
  @Query("select p from Post p where p.created >= ?1")
  Page<Post> searchByCreatedAfter(LocalDateTime createdAfter, Pageable page);

  @Query("select p from Post p where p.author = ?1 and p.title like concat('%', ?2, '%')")
  Page<Post> searchByAuthorAndTitleContains(String author, String title, Pageable pageable);
}
