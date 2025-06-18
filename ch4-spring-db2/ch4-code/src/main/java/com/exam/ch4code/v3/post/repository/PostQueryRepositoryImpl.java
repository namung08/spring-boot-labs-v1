package com.exam.ch4code.v3.post.repository;

import com.exam.ch4code.v3.post.model.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class PostQueryRepositoryImpl implements PostQueryRepository {
  private final JPAQueryFactory factory;
  @Override
  public Page<Post> searchByCreatedAtWithQueryDSL(LocalDateTime createdAt, Pageable pageable) {
    return null;
  }
}
