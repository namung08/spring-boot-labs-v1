package com.exam.ch4code.v2.web.controller;

import com.exam.ch4code.v2.post.service.PostService;
import com.exam.ch4code.v2.web.dto.common.CommonResponse;
import com.exam.ch4code.v2.web.dto.post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v2/posts")
public class PostController {
  private final PostService service;

  @PostMapping
  public ResponseEntity<CommonResponse<PostResponse>> createPost(@RequestBody PostCreateRequest req) {
    return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.success(service.createPost(req)));
  }

  @GetMapping
  public ResponseEntity<CommonResponse<PostPageResponse>> getPosts(@ModelAttribute PostSearchRequest searchRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(service.getPosts(searchRequest)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CommonResponse<PostResponse>> getPost(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(service.getPost(id)));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CommonResponse<PostResponse>> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest req) {
    return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(service.updatePost(id, req)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CommonResponse<String>> deletePost(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(service.deletePost(id)));
  }
}
