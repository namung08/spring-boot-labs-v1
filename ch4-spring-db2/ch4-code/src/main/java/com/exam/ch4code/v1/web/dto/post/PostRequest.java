package com.exam.ch4code.v1.web.dto.post;


import com.exam.ch4code.v1.post.model.Post;

public interface PostRequest {
  Post toDomain();
}
