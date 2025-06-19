package com.example.ch4labs.web.dto.review.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
  private String sort = "createdAt,desc";
  private Integer page = 0;
  private Integer size = 10;
}
