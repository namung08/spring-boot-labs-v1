package com.namung08.ch3codeyourself.post.service;

import com.namung08.ch3codeyourself.post.mapper.PostMapper;
import com.namung08.ch3codeyourself.post.model.Post;
import com.namung08.ch3codeyourself.web.dto.post.PostCreateRequest;
import com.namung08.ch3codeyourself.web.dto.post.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
  private final PostMapper mapper;

  @Override
  public PostResponse createPost(PostCreateRequest req) {
    Post createPost = req.toDomain();
    mapper.save(createPost);
    return PostResponse.from(createPost);
  }

  @Override
  public List<PostResponse> getPosts() {
    return mapper.findAll().stream().map(PostResponse::from).toList();
  }
}
