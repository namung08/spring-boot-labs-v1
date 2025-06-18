package com.exam.ch4code.v1.web.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostSearchRequest {
  private Integer page = 1;
  private Integer size = 10;
}
