package com.example.ch4labs.web.dto.review.request;

import com.example.ch4labs.review.model.Review;
import lombok.Builder;

@Builder
public record ReviewCreateRequest(
    String title,
    String content,
    String author,
    String bookTitle,
    String bookAuthor,
    Integer rating
) implements ReviewRequest {
  @Override
  public Review toDomain() {
    return Review.builder()
                 .title(title)
                 .content(content)
                 .author(author)
                 .bookTitle(bookTitle)
                 .bookAuthor(bookAuthor)
                 .rating(rating)
                 .build();
  }
}
