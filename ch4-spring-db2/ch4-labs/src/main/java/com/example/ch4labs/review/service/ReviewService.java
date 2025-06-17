package com.example.ch4labs.review.service;

import com.example.ch4labs.web.dto.review.request.ReviewCreateRequest;
import com.example.ch4labs.web.dto.review.request.ReviewUpdateRequest;
import com.example.ch4labs.web.dto.review.response.ReviewResponse;

import java.util.List;

public interface ReviewService {
  ReviewResponse createReview(ReviewCreateRequest req);
  List<ReviewResponse> geReviews();
  ReviewResponse updateReview(Long id, ReviewUpdateRequest req);
  void deleteReview(Long id);
}
