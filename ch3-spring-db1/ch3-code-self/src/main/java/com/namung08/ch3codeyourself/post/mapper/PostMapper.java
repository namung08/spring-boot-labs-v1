package com.namung08.ch3codeyourself.post.mapper;

import com.namung08.ch3codeyourself.post.model.Post;
import com.namung08.ch3codeyourself.web.dto.post.PostSearchRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    void save(Post post);
    List<Post> findAll(@Param("req") PostSearchRequest req);

    int updateById(@Param("id") Long id, @Param("post") Post updatePost);

    Post findById(@Param("id") Long id);

    int deleteById(@Param("id") Long id);

    int countBySearch(@Param("req") PostSearchRequest req);
}
