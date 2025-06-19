package com.example.ch4labs.web.controller;

import com.example.ch4labs.review.service.ReviewService;
import com.example.ch4labs.web.dto.review.request.ReviewCreateRequest;
import com.example.ch4labs.web.dto.review.request.ReviewSearchRequest;
import com.example.ch4labs.web.dto.review.request.ReviewUpdateRequest;
import com.example.ch4labs.web.dto.review.response.ReviewResponse;
import com.example.ch4labs.web.dto.review.response.ReviewWithCommentsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
  private final ReviewService service;

  @PostMapping
  public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewCreateRequest req) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.createReview(req));
  }

  @GetMapping
  public ResponseEntity<Page<ReviewResponse>> getReviews(ReviewSearchRequest req) {
    return ResponseEntity.status(HttpStatus.OK).body(service.getReviews(req));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ReviewResponse> updateReview(@PathVariable Long id, @RequestBody ReviewUpdateRequest req) {
    return ResponseEntity.status(HttpStatus.OK).body(service.updateReview(id, req));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ReviewResponse> deleteReview(@PathVariable Long id) {
    service.deleteReview(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ReviewWithCommentsResponse> getReview(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(service.getReviewWithComments(id));
  }
}
