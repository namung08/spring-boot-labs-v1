package com.example.ch4labs.web.dto.comment.request;

import com.example.ch4labs.comment.model.Comment;
import com.example.ch4labs.web.dto.Request;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentUpdateRequest implements Request<Comment> {
  private String content;
  @Override
  public Comment toDomain() {
    return Comment.builder()
                  .content(content)
                  .build();
  }
}
