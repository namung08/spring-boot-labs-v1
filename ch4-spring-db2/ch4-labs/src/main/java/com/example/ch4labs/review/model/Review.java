package com.example.ch4labs.review.model;

import com.example.ch4labs.comment.model.Comment;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String content;
  private String author;
  private String bookTitle;
  private String bookAuthor;
  private Integer rating;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "review", orphanRemoval = true )
  private List<Comment> comment;
}
