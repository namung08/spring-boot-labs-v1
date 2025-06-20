package com.example.ch4labs.web.dto.comment.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentSearchRequest {
  @Builder.Default
  private int page = 0;
  @Builder.Default
  private int size = 10;
  @Builder.Default
  private String sort = "createdAt,desc";
}
