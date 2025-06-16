package com.namung08.ch3codeyourself.post.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Setter
  @Column(nullable = false)
  private String title;
  @Setter
  private String body;


}
