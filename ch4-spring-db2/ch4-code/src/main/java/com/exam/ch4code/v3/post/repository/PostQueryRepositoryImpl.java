package com.exam.ch4code.v3.post.repository;

import com.exam.ch4code.v3.post.model.Post;
import com.exam.ch4code.v3.post.model.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class PostQueryRepositoryImpl implements PostQueryRepository {
  private final JPAQueryFactory factory;
  @Override
  public Page<Post> searchByCreatedAtWithQueryDSL(LocalDateTime createdAt, Pageable pageable) {
    QPost post = QPost.post;

    List<Post> fetch = factory.selectFrom(post)
                              .where(post.created.goe(createdAt))
                              .offset(pageable.getOffset())
                              .limit(pageable.getPageSize())
                              .fetch();

    Long postCount = factory.select(post.count())
                    .from(post)
                    .where(post.created.goe(createdAt))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetchOne();

    return new PageImpl<>(fetch, pageable, postCount == null ? 0 : postCount);
  }
}
