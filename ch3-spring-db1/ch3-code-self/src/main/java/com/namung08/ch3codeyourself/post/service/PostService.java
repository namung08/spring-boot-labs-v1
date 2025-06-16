package com.namung08.ch3codeyourself.post.service;

import com.namung08.ch3codeyourself.web.dto.post.PostCreateRequest;
import com.namung08.ch3codeyourself.web.dto.post.PostResponse;

import java.util.List;

public interface PostService {
  PostResponse createPost(PostCreateRequest req);

  List<PostResponse> getPosts();
}
