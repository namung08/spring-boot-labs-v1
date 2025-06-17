package com.exam.ch4code.v1.post.service;

import com.exam.ch4code.v1.infrastructur.exception.PostException;
import com.exam.ch4code.v1.infrastructur.exception.PostExceptionCode;
import com.exam.ch4code.v1.post.model.Post;
import com.exam.ch4code.v1.post.repository.PostRepository;
import com.exam.ch4code.v1.web.dto.post.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
  private final PostRepository repository;

  @Override
  public PostResponse createPost(PostCreateRequest req) {
    Post post = req.toDomain();
    return PostResponse.from(repository.save(post));
  }

  @Override
  @Transactional(readOnly = true)
  public PostPageResponse getPosts(PostSearchRequest req) {
    Pageable page = PageRequest.of(req.getPage()-1, req.getSize());
    Page<Post> posts = repository.findByTitleContainsOrBodyContains(req.getKeyword(), page);
    log.info(posts.toString());
    return PostPageResponse.builder()
                           .page(req.getPage())
                           .size(req.getSize())
                           .totalCount(posts.getTotalElements())
                           .totalPage(posts.getTotalPages())
                           .posts(posts.stream().map(PostResponse::from).toList())
                           .build();
  }

  @Override
  @Transactional
  public PostResponse updatePost(Long id, PostUpdateRequest req) {
    Post post = repository.findById(id).orElseThrow(() -> new PostException(PostExceptionCode.POST_NOT_FOUND));

    post.setTitle(req.title());
    post.setBody(req.body());

    return PostResponse.from(post);
  }

  @Override
  public PostResponse getPost(Long id) {
    Post post = repository.findById(id).orElseThrow(() -> new PostException(PostExceptionCode.POST_NOT_FOUND));
    return PostResponse.from(post);
  }

  @Override
  public String deletePost(Long id) {
    Post post = repository.findById(id).orElseThrow(() -> new PostException(PostExceptionCode.POST_NOT_FOUND));
    repository.delete(post);
    return "deleted success";
  }
}
