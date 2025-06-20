package com.example.ch4labs.web.controller;

import com.example.ch4labs.comment.sevice.CommentService;
import com.example.ch4labs.web.dto.comment.request.CommentCreateRequest;
import com.example.ch4labs.web.dto.comment.request.CommentSearchRequest;
import com.example.ch4labs.web.dto.comment.request.CommentUpdateRequest;
import com.example.ch4labs.web.dto.comment.response.CommentPageResponse;
import com.example.ch4labs.web.dto.comment.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review/{reviewId}/comments")
@RequiredArgsConstructor
public class CommentController {
  private final CommentService service;

  @PostMapping
  public ResponseEntity<CommentResponse> createComment(@PathVariable Long reviewId, @RequestBody CommentCreateRequest req) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.createComment(reviewId, req));
  }

  @GetMapping
  public ResponseEntity<CommentPageResponse> getAllComments(@PathVariable Long reviewId, CommentSearchRequest req) {
    return ResponseEntity.status(HttpStatus.OK).body(service.getComments(reviewId, req));
  }

  @PutMapping("/{commentId}")
  public ResponseEntity<CommentResponse> updateComment(@PathVariable Long reviewId, @PathVariable Long commentId, @RequestBody CommentUpdateRequest req) {
    return ResponseEntity.status(HttpStatus.OK).body(service.updateComment(reviewId, commentId, req));
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<CommentResponse> deleteComment(@PathVariable Long reviewId, @PathVariable Long commentId) {
    service.deleteComment(reviewId, commentId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
