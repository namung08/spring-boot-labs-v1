package com.captainyun7.postappwithsecurity.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshToken {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String token;

 private Instant expiresAt;

 @OneToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "user_id", referencedColumnName = "id")
 private User user;
}
