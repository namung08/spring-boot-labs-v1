package com.namung08.ch3codeyourself.post.mapper;

import com.namung08.ch3codeyourself.post.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    void save(Post post);
    List<Post> findAll();

    int updateById(@Param("id") Long id, @Param("post") Post updatePost);

    Post findById(@Param("id") Long id);

    int deleteById(@Param("id") Long id);
}
