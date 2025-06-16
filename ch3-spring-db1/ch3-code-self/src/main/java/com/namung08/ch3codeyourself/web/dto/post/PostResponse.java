package com.namung08.ch3codeyourself.web.dto.post;

import com.namung08.ch3codeyourself.post.model.Post;
import lombok.Builder;

@Builder
public record PostResponse(
    Long id,
    String title,
    String body
) {
  public static PostResponse from(Post post) {
    return PostResponse.builder()
                       .id(post.getId())
                       .body(post.getBody())
                       .title(post.getTitle())
                       .build();
  }
}
