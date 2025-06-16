package com.namung08.ch3codeyourself.web.controller;

import com.namung08.ch3codeyourself.post.service.PostService;
import com.namung08.ch3codeyourself.web.dto.post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
public class PostController {
  private final PostService service;

  @PostMapping
  public ResponseEntity<PostResponse> createPost(@RequestBody PostCreateRequest req) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.createPost(req));
  }

  @GetMapping
  public ResponseEntity<PostPageResponse> getPosts(@ModelAttribute PostSearchRequest searchRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(service.getPosts(searchRequest));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(service.getPost(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PostResponse> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest req) {
    return ResponseEntity.status(HttpStatus.OK).body(service.updatePost(id, req));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PostResponse> deletePost(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(service.deletePost(id));
  }
}
