package com.exam.ch4code.post.service;

import com.exam.ch4code.web.dto.post.*;

public interface PostService {
  PostResponse createPost(PostCreateRequest req);

  PostPageResponse getPosts(PostSearchRequest req);

  PostResponse updatePost(Long id, PostUpdateRequest req);

  PostResponse getPost(Long id);

  String deletePost(Long id);
}
