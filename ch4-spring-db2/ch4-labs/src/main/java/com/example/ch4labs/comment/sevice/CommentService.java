package com.example.ch4labs.comment.sevice;

import com.example.ch4labs.web.dto.comment.request.CommentCreateRequest;
import com.example.ch4labs.web.dto.comment.request.CommentSearchRequest;
import com.example.ch4labs.web.dto.comment.request.CommentUpdateRequest;
import com.example.ch4labs.web.dto.comment.response.CommentPageResponse;
import com.example.ch4labs.web.dto.comment.response.CommentResponse;

public interface CommentService {
  CommentResponse createComment(Long postId, CommentCreateRequest req);

  CommentPageResponse getComments(Long reviewId, CommentSearchRequest req);

  CommentResponse updateComment(Long reviewId, Long commentId, CommentUpdateRequest req);

  void deleteComment(Long reviewId, Long commentId);
}
