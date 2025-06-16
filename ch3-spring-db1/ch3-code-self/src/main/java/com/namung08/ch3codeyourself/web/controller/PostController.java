package com.namung08.ch3codeyourself.web.controller;

import com.namung08.ch3codeyourself.post.service.PostService;
import com.namung08.ch3codeyourself.web.dto.post.PostCreateRequest;
import com.namung08.ch3codeyourself.web.dto.post.PostResponse;
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
  public ResponseEntity<List<PostResponse>> getPosts() {
    return ResponseEntity.status()
  }
}
