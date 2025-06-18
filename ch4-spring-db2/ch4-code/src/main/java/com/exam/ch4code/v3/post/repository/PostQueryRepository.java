package com.exam.ch4code.v3.post.repository;

import com.exam.ch4code.v3.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface PostQueryRepository {
  // 작성 일자 이후 조회
  Page<Post> searchByCreatedAtWithQueryDSL(LocalDateTime createdAt, Pageable pageable);
}
