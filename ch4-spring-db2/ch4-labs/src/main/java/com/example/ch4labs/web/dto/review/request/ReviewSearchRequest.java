package com.example.ch4labs.web.dto.review.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewSearchRequest {
  private String bookTitle;
  private String author;
  private Integer rating;
  private Integer minRating;
  private Integer maxRating;
  private Integer page = 0;
  private Integer size = 10;
}
