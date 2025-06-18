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
  public Page<ReviewResponse> geReviews(ReviewSearchRequest req) {
    Pageable pageable = PageRequest.of(req.getPage(), req.getSize());
    Page<Review> reviews = repository.findAll(pageable);
    if(req.getBookTitle() != null && !req.getBookTitle().isBlank()) {
      reviews.filter(review -> review.getBookTitle().contains(req.getBookTitle()));
    }
    if (req.getAuthor() != null && !req.getAuthor().isBlank()) {
      reviews.filter(review -> review.getAuthor().equals(req.getAuthor()));
    }
    if(req.getRating() != null) {
      reviews.filter(review -> review.getRating().equals(req.getRating()));
    }
    if(req.getMinRating() != null) {
      reviews.filter(review -> review.getRating() >= req.getMinRating());
    }
    if(req.getMaxRating() != null) {
      reviews.filter(review -> review.getRating() <= req.getMaxRating());
    }
    return reviews.map(ReviewResponse::from);
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
