package com.exam.ch4code.v3.post.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Profile;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Profile("v2")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  private String body;
}
