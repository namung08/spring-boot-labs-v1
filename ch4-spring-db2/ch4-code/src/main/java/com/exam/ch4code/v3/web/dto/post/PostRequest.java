package com.exam.ch4code.v3.web.dto.post;


import com.exam.ch4code.v3.post.model.Post;

public interface PostRequest {
  Post toDomain();
}
