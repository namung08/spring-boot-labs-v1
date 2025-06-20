package com.example.ch4labs.web.dto.review.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewSearchRequest {
  private String author;
  private String bookTitle;
  private String bookTitleContains;
  private String bookAuthor;
  private String titleContains;
  private String contentContains;
  private Integer rating;
  private Integer minRating;
  private Integer maxRating;
  @Builder.Default
  private String sort = "createdAt,desc";
  @Builder.Default
  private Integer page = 0;
  @Builder.Default
  private Integer size = 10;
}
