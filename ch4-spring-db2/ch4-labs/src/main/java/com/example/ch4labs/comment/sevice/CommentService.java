package com.example.ch4labs.comment.sevice;

import com.example.ch4labs.web.dto.comment.request.CommentCreateRequest;
import com.example.ch4labs.web.dto.comment.response.CommentResponse;

public interface CommentService {
  CommentResponse createComment(Long postId, CommentCreateRequest req);
}
