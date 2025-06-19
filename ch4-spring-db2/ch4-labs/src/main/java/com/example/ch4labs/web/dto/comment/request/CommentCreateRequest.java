package com.example.ch4labs.web.dto.comment.request;

import com.example.ch4labs.comment.model.Comment;
import com.example.ch4labs.web.dto.Request;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentCreateRequest implements Request<Comment> {
  private Long reviewId;
  private String content;
  private String author;
  @Override
  public Comment toDomain() {
    return Comment.builder()
                  .content(content)
                  .author(author)
                  .build();
  }
}
