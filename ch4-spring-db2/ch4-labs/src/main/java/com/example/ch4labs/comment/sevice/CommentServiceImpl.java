package com.example.ch4labs.comment.sevice;

import com.example.ch4labs.comment.model.Comment;
import com.example.ch4labs.comment.repository.CommentRepository;
import com.example.ch4labs.infrastructure.exception.review.ReviewException;
import com.example.ch4labs.infrastructure.exception.review.ReviewExceptionCode;
import com.example.ch4labs.review.repository.ReviewRepository;
import com.example.ch4labs.review.service.ReviewService;
import com.example.ch4labs.web.dto.comment.request.CommentCreateRequest;
import com.example.ch4labs.web.dto.comment.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
  private final CommentRepository repository;
  private final ReviewRepository reviewRepository;

  @Override
  public CommentResponse createComment(Long postId, CommentCreateRequest req) {
    Comment comment = req.toDomain();
    comment.setReview(reviewRepository.findById(postId).orElseThrow(() -> new ReviewException(
        ReviewExceptionCode.REVIEW_NOT_FOUND)));
    return CommentResponse.from(repository.save(comment));
  }
}
