package com.example.ch4labs.web.dto.review.response;

import com.example.ch4labs.review.model.Review;
import lombok.Builder;

@Builder
public record ReviewResponse(
    Long id,
    String title,
    String content,
    String author,
    String bookTitle,
    String bookAuthor,
    Integer rating
) {
  public static ReviewResponse from(Review review) {
    return ReviewResponse.builder()
                         .id(review.getId())
                         .title(review.getTitle())
                         .content(review.getContent())
                         .author(review.getAuthor())
                         .bookTitle(review.getBookTitle())
                         .bookAuthor(review.getBookAuthor())
                         .rating(review.getRating())
                         .build();
  }
}
