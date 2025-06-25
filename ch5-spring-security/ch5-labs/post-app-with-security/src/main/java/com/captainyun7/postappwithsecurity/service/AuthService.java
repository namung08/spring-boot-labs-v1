package com.captainyun7.postappwithsecurity.service;

import com.captainyun7.postappwithsecurity.domain.RefreshToken;
import com.captainyun7.postappwithsecurity.domain.User;
import com.captainyun7.postappwithsecurity.dto.user.request.LoginRequest;
import com.captainyun7.postappwithsecurity.dto.user.request.RegisterRequest;
import com.captainyun7.postappwithsecurity.dto.user.response.LoginResponse;
import com.captainyun7.postappwithsecurity.dto.user.response.UserResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
 private final UserService userService;
 private final PasswordEncoder encoder;
 private final AuthenticationManager manager;
 private final JwtUtil jwtUtil;
 private final RefreshTokenService refreshTokenService;

 public UserResponse register(RegisterRequest req) {
  if(userService.existsByUsername(req.getUsername())) {
   throw new EntityExistsException("Username is already in use");
  }
  req.encode(encoder);
  log.info("encoded password = {}", req.getPassword());

  return userService.createUser(req);
 }

 public void login(LoginRequest req, HttpServletResponse res) {
  Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
  SecurityContextHolder.getContext().setAuthentication(authenticate);
  UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
  String accessToken = jwtUtil.createAccessToken(userDetails);
  RefreshToken refreshToken = refreshTokenService.createRefreshToken(req.getUsername());
  res.addHeader("Authorization", "Bearer " + accessToken);
  res.addCookie(createCookie("refresh", refreshToken));
 }

 public Cookie createCookie(String key, RefreshToken token) {
  Cookie cookie = new Cookie(key, token.getToken());
  cookie.setMaxAge(60 * 60);
  cookie.setHttpOnly(true);
  cookie.setPath("/");
  cookie.setSecure(true);

  return cookie;
 }
}
