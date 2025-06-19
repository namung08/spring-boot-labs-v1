package com.example.ch4labs.comment.sevice;

import com.example.ch4labs.comment.model.Comment;
import com.example.ch4labs.comment.repository.CommentRepository;
import com.example.ch4labs.infrastructure.exception.review.ReviewException;
import com.example.ch4labs.infrastructure.exception.review.ReviewExceptionCode;
import com.example.ch4labs.review.model.Review;
import com.example.ch4labs.review.repository.ReviewRepository;
import com.example.ch4labs.web.dto.comment.request.CommentCreateRequest;
import com.example.ch4labs.web.dto.comment.request.CommentUpdateRequest;
import com.example.ch4labs.web.dto.comment.response.CommentPageResponse;
import com.example.ch4labs.web.dto.comment.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

  @Override
  public CommentPageResponse getComments(Long reviewId, int page, int size) {
    Page<Comment> byReviewId = repository.findByReview_Id(reviewId, PageRequest.of(page, size));
    return CommentPageResponse.from(byReviewId.map(CommentResponse::from));
  }

  @Override
  @Transactional
  public CommentResponse updateComment(Long reviewId, Long commentId, CommentUpdateRequest req) {
    Comment comment = getComment(reviewId, commentId);

    comment.setContent(req.getContent());

    return CommentResponse.from(comment);
  }

  @Override
  public void deleteComment(Long reviewId, Long commentId) {
    Comment comment = getComment(reviewId, commentId);
    repository.delete(comment);
  }

  private Comment getComment(Long reviewId, Long commentId) {
    Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewException(ReviewExceptionCode.REVIEW_NOT_FOUND));

    Comment comment = review.getComment().stream().filter(c -> c.getId().equals(commentId)).findFirst()
                            .orElseThrow(() -> new ReviewException(ReviewExceptionCode.COMMENT_NOT_FOUND));

    Comment comment1 = repository.findById(commentId).orElseThrow(() -> new ReviewException(ReviewExceptionCode.COMMENT_NOT_FOUND));

    if(!comment.equals(comment1)) {
      throw new ReviewException(ReviewExceptionCode.NOT_MATCH_COMMENT);
    }
    return comment1;
  }
}
