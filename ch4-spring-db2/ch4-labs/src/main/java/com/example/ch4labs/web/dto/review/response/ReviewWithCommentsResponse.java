package com.example.ch4labs.web.dto.review.response;

import com.example.ch4labs.comment.model.Comment;
import com.example.ch4labs.review.model.Review;
import com.example.ch4labs.web.dto.comment.response.CommentPageResponse;
import com.example.ch4labs.web.dto.comment.response.CommentResponse;
import lombok.*;
import org.springframework.data.domain.Page;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewWithCommentsResponse {
  private ReviewResponse reviewResponse;
  private CommentPageResponse comments;

  public static ReviewWithCommentsResponse from(Review review, Page<Comment> comments) {
    return ReviewWithCommentsResponse.builder()
        .reviewResponse(ReviewResponse.from(review))
        .comments(CommentPageResponse.from(comments.map(CommentResponse::from)))
        .build();
  }

  public static ReviewWithCommentsResponse from(Review review) {
    return ReviewWithCommentsResponse.builder()
        .reviewResponse(ReviewResponse.from(review))
        .comments(CommentPageResponse.from(null))
        .build();
  }
}
