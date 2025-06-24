package com.captainyun7.postappwithsecurity.controller;

import com.captainyun7.postappwithsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
 private final AuthService service;
}
