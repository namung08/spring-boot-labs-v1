package com.example.ch4labs.web.controller;

import com.example.ch4labs.comment.repository.CommentRepository;
import com.example.ch4labs.comment.sevice.CommentService;
import com.example.ch4labs.web.dto.comment.request.CommentCreateRequest;
import com.example.ch4labs.web.dto.comment.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {
  private final CommentService service;

  @PostMapping("/posts/{postId}/comments")
  public ResponseEntity<CommentResponse> createComment(@PathVariable Long postId, @RequestBody CommentCreateRequest req) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.createComment(postId, req));
  }
}
