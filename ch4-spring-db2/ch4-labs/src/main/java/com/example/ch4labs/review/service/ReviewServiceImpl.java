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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.ch4labs.review.specification.ReviewSpecification.*;

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
    Specification<Review> spec = (root, query, cb) -> cb.conjunction();
    Pageable pageable = PageRequest.of(req.getPage(), req.getSize());
    Page<Review> reviews;
    if(req.getBookTitle() != null) {
      spec = spec.and(byBookTitleContains(req.getBookTitle()));
    }
    if (req.getAuthor() != null) {
      spec = spec.and(byAuthor(req.getAuthor()));
    }
    if(req.getRating() != null) {
      spec = spec.and(byRating(req.getRating()));
    }
    if(req.getMinRating() != null) {
      spec = spec.and(byGreaterMinRating(req.getMinRating()));
    }
    if(req.getMaxRating() != null) {
      spec = spec.and(byLessMaxRating(req.getMaxRating()));
    }
    reviews = repository.findAll(spec, pageable);

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
