package com.exam.ch4code.v1.web.dto.post;

import com.exam.ch4code.v1.post.model.Post;
import lombok.Builder;

@Builder
public record PostCreateRequest(
    String title,
    String body
) {
  public Post toDomain() {
    return Post.builder()
               .title(title)
               .body(body)
               .build();
  }
}
