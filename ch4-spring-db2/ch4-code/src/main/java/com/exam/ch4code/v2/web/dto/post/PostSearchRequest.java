package com.exam.ch4code.v2.web.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostSearchRequest {
  private Integer page = 0;
  private Integer size = 10;
  private String keyword = "";
}
