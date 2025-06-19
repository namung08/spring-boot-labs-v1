package com.example.ch4labs.review.service;

import com.example.ch4labs.infrastructure.exception.review.ReviewException;
import com.example.ch4labs.infrastructure.exception.review.ReviewExceptionCode;
import com.example.ch4labs.review.model.Review;
import com.example.ch4labs.review.repository.ReviewRepository;
import com.example.ch4labs.web.dto.review.request.ReviewCreateRequest;
import com.example.ch4labs.web.dto.review.request.ReviewSearchRequest;
import com.example.ch4labs.web.dto.review.request.ReviewUpdateRequest;
import com.example.ch4labs.web.dto.review.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
  private final ReviewRepository repository;

  @Override
  public ReviewResponse createReview(ReviewCreateRequest req) {
    return ReviewResponse.from(repository.save(req.toDomain()));
  }

  @Override
  public Page<ReviewResponse> getReviews(ReviewSearchRequest req) {
    Page<ReviewResponse> reviews = repository.searchReview(req);

    return reviews;
  }

  @Override
  @Transactional
  public ReviewResponse updateReview(Long id, ReviewUpdateRequest req) {
    Review review = repository.findById(id).orElseThrow(() -> new ReviewException(ReviewExceptionCode.REVIEW_NOT_FOUND));
    req.update(review, req.toDomain());
    return ReviewResponse.from(review);
  }

  @Override
  public void deleteReview(Long id) {
    Review review = repository.findById(id).orElseThrow(() -> new ReviewException(ReviewExceptionCode.REVIEW_NOT_FOUND));
    repository.delete(review);
  }

  @Override
  public Review getReview(Long id) {
    return repository.findById(id).orElseThrow(() -> new ReviewException(ReviewExceptionCode.REVIEW_NOT_FOUND));
  }
}
