package com.namung08.ch3codeyourself.web.dto.post;

import com.namung08.ch3codeyourself.post.model.Post;
import lombok.Builder;

@Builder
public record PostUpdateRequest(
    String title,
    String body
) implements PostRequest{
  @Override
  public Post toDomain() {
    return Post.builder()
               .title(title)
               .body(body)
               .build();
  }
}
