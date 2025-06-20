package com.example.ch4labs.web.dto.comment.response;

import com.example.ch4labs.comment.model.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse {
  private Long id;
  private String content;
  private String author;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Long reviewId;

  public static CommentResponse from(Comment domain) {
    return CommentResponse.builder()
                          .id(domain.getId())
                          .content(domain.getContent())
                          .author(domain.getAuthor())
                          .createdAt(domain.getCreatedAt())
                          .updatedAt(domain.getUpdatedAt())
                          .reviewId(domain.getReview()
                                          .getId())
                          .build();
  }
}
