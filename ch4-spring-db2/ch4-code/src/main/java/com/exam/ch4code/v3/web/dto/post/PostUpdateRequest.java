package com.exam.ch4code.v3.web.dto.post;

import com.exam.ch4code.v3.post.model.Post;
import lombok.Builder;

@Builder
public record PostUpdateRequest(
    String title,
    String body
) implements PostRequest {
  @Override
  public Post toDomain() {
    return Post.builder()
               .title(title)
               .body(body)
               .build();
  }
}
