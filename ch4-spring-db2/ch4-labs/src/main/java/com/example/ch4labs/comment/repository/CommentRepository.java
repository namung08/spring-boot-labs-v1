package com.example.ch4labs.comment.repository;

import com.example.ch4labs.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
