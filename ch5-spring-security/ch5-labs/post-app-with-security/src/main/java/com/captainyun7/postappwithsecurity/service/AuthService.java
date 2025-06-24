package com.captainyun7.postappwithsecurity.service;

import com.captainyun7.postappwithsecurity.domain.User;
import com.captainyun7.postappwithsecurity.dto.user.request.RegisterRequest;
import com.captainyun7.postappwithsecurity.dto.user.response.UserResponse;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
 private final UserService userService;
 private final PasswordEncoder encoder;
 public UserResponse register(RegisterRequest req) {
  if(userService.existsByUsername(req.getUsername())) {
   throw new EntityExistsException("Username is already in use");
  }
  req.encode(encoder);
  log.info("encoded password = {}", req.getPassword());

  return userService.createUser(req);
 }
}
