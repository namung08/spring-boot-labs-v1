package com.example.ch4labs.review.service;

import com.example.ch4labs.comment.model.Comment;
import com.example.ch4labs.comment.repository.CommentRepository;
import com.example.ch4labs.infrastructure.exception.review.ReviewException;
import com.example.ch4labs.infrastructure.exception.review.ReviewExceptionCode;
import com.example.ch4labs.review.model.Review;
import com.example.ch4labs.review.repository.ReviewRepository;
import com.example.ch4labs.web.dto.review.request.ReviewCreateRequest;
import com.example.ch4labs.web.dto.review.request.ReviewDetailRequest;
import com.example.ch4labs.web.dto.review.request.ReviewSearchRequest;
import com.example.ch4labs.web.dto.review.request.ReviewUpdateRequest;
import com.example.ch4labs.web.dto.review.response.ReviewResponse;
import com.example.ch4labs.web.dto.review.response.ReviewWithCommentsResponse;
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
  private final CommentRepository commentRepository;

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

  @Override
  public ReviewWithCommentsResponse getReviewWithComments(Long id, ReviewDetailRequest req) {
    Review review = repository.findById(id)
                              .orElseThrow(() -> new ReviewException(ReviewExceptionCode.REVIEW_NOT_FOUND));
    if(req.isIncludeComments()) {
      Pageable pageable = PageRequest.of(req.getPage(), req.getSize());
      Page<Comment> comments = commentRepository.findByReview_IdAndParentNull(id, pageable);
      
      return ReviewWithCommentsResponse.from(review, comments);
    }

    return ReviewWithCommentsResponse.from(review);
  }
}
