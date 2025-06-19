package com.example.ch4labs.web.dto.review.response;

import com.example.ch4labs.review.model.Review;
import com.example.ch4labs.web.dto.comment.response.CommentResponse;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewWithCommentsResponse {
  private ReviewResponse reviewResponse;
  private List<CommentResponse> comments;

  public static ReviewWithCommentsResponse from(Review review) {
    return ReviewWithCommentsResponse.builder()
        .reviewResponse(ReviewResponse.from(review))
        .comments(review.getComment().stream().map(CommentResponse::from).toList())
        .build();
  }
}
