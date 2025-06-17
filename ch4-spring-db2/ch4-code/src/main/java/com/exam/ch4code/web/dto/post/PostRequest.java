package com.exam.ch4code.web.dto.post;


import com.exam.ch4code.post.model.Post;

public interface PostRequest {
  Post toDomain();
}
