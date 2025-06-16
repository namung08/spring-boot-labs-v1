package com.namung08.ch3codeyourself.post.service;

import com.namung08.ch3codeyourself.web.dto.post.*;

import java.util.List;

public interface PostService {
  PostResponse createPost(PostCreateRequest req);

  PostPageResponse getPosts(PostSearchRequest req);

  PostResponse updatePost(Long id, PostUpdateRequest req);

  PostResponse getPost(Long id);

  PostResponse deletePost(Long id);
}
