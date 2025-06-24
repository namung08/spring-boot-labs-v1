package com.captainyun7.postappwithsecurity.domain;

import com.captainyun7.postappwithsecurity.dto.user.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String username;

 private String password;

 @Enumerated(EnumType.STRING)
 private Role role;

 @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<Post> posts = new ArrayList<>();

 @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<Comment> comments = new ArrayList<>();
}
