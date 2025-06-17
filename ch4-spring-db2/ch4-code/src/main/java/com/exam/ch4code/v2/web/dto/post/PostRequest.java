package com.exam.ch4code.v2.web.dto.post;


import com.exam.ch4code.v2.post.model.Post;

public interface PostRequest {
  Post toDomain();
}
