package com.example.ch4labs.web.controller;

import com.example.ch4labs.comment.sevice.CommentService;
import com.example.ch4labs.web.dto.comment.request.CommentCreateRequest;
import com.example.ch4labs.web.dto.comment.response.CommentPageResponse;
import com.example.ch4labs.web.dto.comment.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
  public ResponseEntity<CommentPageResponse> getAllComments(@PathVariable Long reviewId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.status(HttpStatus.OK).body(service.getCommends(reviewId, page, size));
  }
}
