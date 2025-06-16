package com.namung08.ch3codeyourself.web.dto.post;

import com.namung08.ch3codeyourself.post.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
  private Long id;
  private String title;
  private String body;
  public static PostResponse from(Post post) {
    return PostResponse.builder()
                       .id(post.getId())
                       .body(post.getBody())
                       .title(post.getTitle())
                       .build();
  }

}
