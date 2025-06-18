package com.exam.ch4code.v3.web.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostSearchRequest {
  private Integer page = 0;
  private Integer size = 10;
  private String keyword;
  private String author;
  private String sort;
  private LocalDate startDate;
  private LocalDate endDate;
  private LocalDateTime createAt;
}
