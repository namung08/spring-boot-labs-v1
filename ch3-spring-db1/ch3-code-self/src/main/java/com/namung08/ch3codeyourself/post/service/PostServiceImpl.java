package com.namung08.ch3codeyourself.post.service;

import com.namung08.ch3codeyourself.post.mapper.PostMapper;
import com.namung08.ch3codeyourself.post.model.Post;
import com.namung08.ch3codeyourself.web.dto.post.PostCreateRequest;
import com.namung08.ch3codeyourself.web.dto.post.PostResponse;
import com.namung08.ch3codeyourself.web.dto.post.PostUpdateRequest;
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

  @Override
  public PostResponse updatePost(Long id, PostUpdateRequest req) {
    Post post = mapper.findById(id);
    post.setBody(req.body());
    post.setTitle(req.title());
    int result = mapper.updateById(id, post);
    if(result == 1) {
      return PostResponse.from(post);
    } else {
      throw new RuntimeException("Error");
    }
  }

  @Override
  public PostResponse getPost(Long id) {
    return PostResponse.from(mapper.findById(id));
  }

  @Override
  public PostResponse deletePost(Long id) {
    Post post = mapper.findById(id);
    int result = mapper.deleteById(id);
    if(result == 1) {
      return PostResponse.from(post);
    } else {
      throw new RuntimeException("Error");
    }
  }
}
