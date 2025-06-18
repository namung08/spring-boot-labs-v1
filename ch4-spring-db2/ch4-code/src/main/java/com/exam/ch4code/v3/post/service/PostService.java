package com.exam.ch4code.v3.post.service;

import com.exam.ch4code.v3.web.dto.post.*;

public interface PostService {
  PostResponse createPost(PostCreateRequest req);

  PostPageResponse getPosts(PostSearchRequest req);

  PostResponse updatePost(Long id, PostUpdateRequest req);

  PostResponse getPost(Long id);

  String deletePost(Long id);
}
