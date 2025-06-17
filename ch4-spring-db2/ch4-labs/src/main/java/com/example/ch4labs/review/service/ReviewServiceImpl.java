package com.example.ch4labs.review.service;

import com.example.ch4labs.infrastructure.exception.ReviewException;
import com.example.ch4labs.infrastructure.exception.ReviewExceptionCode;
import com.example.ch4labs.review.model.Review;
import com.example.ch4labs.review.repository.ReviewRepository;
import com.example.ch4labs.web.dto.review.request.ReviewCreateRequest;
import com.example.ch4labs.web.dto.review.request.ReviewUpdateRequest;
import com.example.ch4labs.web.dto.review.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
  private final ReviewRepository repository;

  @Override
  public ReviewResponse createReview(ReviewCreateRequest req) {
    return ReviewResponse.from(repository.save(req.toDomain()));
  }

  @Override
  public List<ReviewResponse> geReviews() {
    return repository.findAll().stream().map(ReviewResponse::from).toList();
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
}
