package com.exam.ch4code.v1.post.repository;

import com.exam.ch4code.v1.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
  @Query("""
         select p from Post p
         where upper(p.title) like upper(concat('%', ?1, '%')) or upper(p.body) like upper(concat('%', ?1, '%'))""")
  Page<Post> findByTitleContainsOrBodyContains(String keyword, Pageable pageable);
}
