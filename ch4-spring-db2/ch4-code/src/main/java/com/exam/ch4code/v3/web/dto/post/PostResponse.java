package com.exam.ch4code.v3.web.dto.post;

import com.exam.ch4code.v3.post.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
  private Long id;
  private String title;
  private String body;
  private String author;
  private LocalDateTime createAt;
  public static PostResponse from(Post post) {
    return PostResponse.builder()
                       .id(post.getId())
                       .body(post.getBody())
                       .title(post.getTitle())
                       .author(post.getAuthor())
                       .createAt(post.getCreated())
                       .build();
  }

}
