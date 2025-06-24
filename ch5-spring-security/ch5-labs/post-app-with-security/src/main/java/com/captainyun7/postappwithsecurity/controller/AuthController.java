package com.captainyun7.postappwithsecurity.controller;

import com.captainyun7.postappwithsecurity.dto.user.request.RegisterRequest;
import com.captainyun7.postappwithsecurity.dto.user.response.UserResponse;
import com.captainyun7.postappwithsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
 private final AuthService service;

 @PostMapping("/signup")
 public ResponseEntity<UserResponse> post(@RequestBody RegisterRequest registerRequest) {
  return ResponseEntity.status(HttpStatus.CREATED).body(service.register(registerRequest));
 }
}
