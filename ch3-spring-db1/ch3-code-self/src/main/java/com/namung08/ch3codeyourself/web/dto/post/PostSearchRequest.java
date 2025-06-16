package com.namung08.ch3codeyourself.web.dto.post;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostSearchRequest {
  private Integer page = 1;
  private Integer size = 10;
  private String keyword = "";

  private int getOffset() {
    return (page - 1) * size;
  }
}
