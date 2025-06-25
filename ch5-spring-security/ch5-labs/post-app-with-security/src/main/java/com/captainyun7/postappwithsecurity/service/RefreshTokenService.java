package com.captainyun7.postappwithsecurity.service;

import com.captainyun7.postappwithsecurity.domain.RefreshToken;
import com.captainyun7.postappwithsecurity.domain.User;
import com.captainyun7.postappwithsecurity.dto.user.request.LoginRequest;
import com.captainyun7.postappwithsecurity.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
 private final UserService userService;
 private final RefreshTokenRepository repository;
 private final JwtUtil jwtUtil;

 public RefreshToken createRefreshToken(String username) {
  User user = userService.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
  RefreshToken refreshToken = repository.findByUser_Id(user.getId()).orElse(null);
  if(refreshToken != null) {
   repository.delete(refreshToken);
  }
  refreshToken = new RefreshToken();
  refreshToken.setUser(user);
  refreshToken.setExpiresAt(Instant.now().plusMillis(jwtUtil.getRefreshExpireMs()));
  refreshToken.setToken(jwtUtil.createRefreshToken(UUID.randomUUID().toString()));

  return repository.save(refreshToken);
 }
}
