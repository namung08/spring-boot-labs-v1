package com.example.ch4labs.review.service;

import com.example.ch4labs.review.model.Review;
import com.example.ch4labs.web.dto.review.request.ReviewCreateRequest;
import com.example.ch4labs.web.dto.review.request.ReviewSearchRequest;
import com.example.ch4labs.web.dto.review.request.ReviewUpdateRequest;
import com.example.ch4labs.web.dto.review.response.ReviewResponse;
import org.springframework.data.domain.Page;

public interface ReviewService {
  ReviewResponse createReview(ReviewCreateRequest req);
  Page<ReviewResponse> getReviews(ReviewSearchRequest req);
  ReviewResponse updateReview(Long id, ReviewUpdateRequest req);
  void deleteReview(Long id);
  Review getReview(Long id);
}
