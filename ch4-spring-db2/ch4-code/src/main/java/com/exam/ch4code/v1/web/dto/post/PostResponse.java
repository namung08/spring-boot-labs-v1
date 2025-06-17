package com.exam.ch4code.v1.web.dto.post;

import com.exam.ch4code.v1.post.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
