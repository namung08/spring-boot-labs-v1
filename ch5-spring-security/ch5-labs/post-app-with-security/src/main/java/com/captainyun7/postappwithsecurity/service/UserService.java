package com.captainyun7.postappwithsecurity.service;

import com.captainyun7.postappwithsecurity.dto.user.request.RegisterRequest;
import com.captainyun7.postappwithsecurity.dto.user.response.UserResponse;
import com.captainyun7.postappwithsecurity.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
 private final UserRepository repository;

 public boolean existsByUsername(String username) {
  return repository.existsByUsername(username);
 }

 public UserResponse createUser(RegisterRequest req) {
  return UserResponse.of(repository.save(req.to()));
 }
}
