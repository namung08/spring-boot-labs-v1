package com.example.ch4labs.web.dto.comment.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentSearchRequest {
  private int page = 0;
  private int size = 10;
  private String sort;
}
