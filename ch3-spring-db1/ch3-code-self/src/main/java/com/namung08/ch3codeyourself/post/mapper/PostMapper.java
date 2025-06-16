package com.namung08.ch3codeyourself.post.mapper;

import com.namung08.ch3codeyourself.post.model.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    void save(Post post);
    List<Post> findAll();
}
