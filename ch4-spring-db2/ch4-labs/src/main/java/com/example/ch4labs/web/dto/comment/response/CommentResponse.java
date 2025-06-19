package com.example.ch4labs.web.dto.comment.response;

import com.example.ch4labs.comment.model.Comment;
import com.example.ch4labs.web.dto.Response;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse  implements Response<Comment, CommentResponse> {
  private Long id;
  private String content;
  private String author;
  private LocalDateTime createdAt;
  private Long reviewId;
  @Override
  public CommentResponse from(Comment domain) {
    return CommentResponse.builder()
                              .id(domain.getId())
                              .content(domain.getContent())
                              .author(domain.getAuthor())
                              .createdAt(domain.getCreatedAt())
                              .reviewId(domain.getReview()
                                              .getId())
                              .build();
  }
}
