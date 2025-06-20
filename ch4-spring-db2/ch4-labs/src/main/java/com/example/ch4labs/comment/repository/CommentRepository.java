package com.example.ch4labs.comment.repository;

import com.example.ch4labs.comment.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  Page<Comment> findByReview_Id(Long id, Pageable pageable);

  Page<Comment> findByReview_IdAndParentNull(Long id, Pageable pageable);
}
