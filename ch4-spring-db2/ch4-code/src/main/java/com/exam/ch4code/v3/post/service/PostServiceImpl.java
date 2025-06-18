package com.exam.ch4code.v3.post.service;

import com.exam.ch4code.v3.exception.PostException;
import com.exam.ch4code.v3.exception.PostExceptionCode;
import com.exam.ch4code.v3.post.model.Post;
import com.exam.ch4code.v3.post.repository.PostRepository;
import com.exam.ch4code.v3.web.dto.post.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
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
    Pageable page = PageRequest.of(req.getPage(), req.getSize());
    Page<Post> posts;
    // 우선순위 정리해서 명확하게 처리
    if (req.getKeyword() != null && req.getAuthor() != null) {
      posts = repository.searchByAuthorAndTitleContains(req.getAuthor(), req.getKeyword(), page);
    } else if (req.getKeyword() != null) {
      posts = repository.findByTitleContains(req.getKeyword(), page);
    } else if (req.getAuthor() != null) {
      posts = repository.findByAuthor(req.getAuthor(), page);
    } else if (req.getCreateAt() != null) {
//      posts = repository.findByCreatedAfter(req.getCreateAt(), page);
      posts = repository.searchByCreatedAfter(req.getCreateAt(), page);
    } else {
      posts = repository.findAll(page);
    }

    return PostPageResponse.builder()
                           .page(posts.getNumber())
                           .size(posts.getSize())
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
