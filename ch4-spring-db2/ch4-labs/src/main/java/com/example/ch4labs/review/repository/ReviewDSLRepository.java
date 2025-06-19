package com.example.ch4labs.review.repository;

import com.example.ch4labs.review.model.Review;
import com.example.ch4labs.web.dto.review.request.ReviewSearchRequest;
import com.example.ch4labs.web.dto.review.response.ReviewResponse;
import org.springframework.data.domain.Page;

public interface ReviewDSLRepository {
  Page<ReviewResponse> searchReview(ReviewSearchRequest req);
}
