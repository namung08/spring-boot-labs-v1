package com.example.ch4labs.web.dto.comment.response;

import com.example.ch4labs.comment.model.Comment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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
  private Long parentId;
  private List<CommentResponse> replies = new LinkedList<>();

  public static CommentResponse from(Comment domain) {
    return CommentResponse.builder()
                          .id(domain.getId())
                          .content(domain.getContent())
                          .author(domain.getAuthor())
                          .createdAt(domain.getCreatedAt())
                          .updatedAt(domain.getUpdatedAt())
                          .reviewId(domain.getReview()
                                          .getId())
                          .parentId(domain.getParent() != null ? domain.getParent().getId() : null)
                          .replies(domain.getChildren() != null ? domain.getChildren().stream().map(CommentResponse::from).toList() : null)
                          .build();
  }

}
