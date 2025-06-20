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
  private String content;
  private String author;
  private Long parentId;
  @Override
  public Comment toDomain() {
    return Comment.builder()
                  .content(content)
                  .author(author)
                  .build();
  }
}
