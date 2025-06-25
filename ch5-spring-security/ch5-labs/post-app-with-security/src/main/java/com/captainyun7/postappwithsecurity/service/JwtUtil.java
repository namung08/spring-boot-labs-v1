package com.captainyun7.postappwithsecurity.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {


 @Value("${spring.jwt.access_expire_second}")
 private Long accessExpireSecond;

 @Value("${spring.jwt.refresh_expire_hour}")
 private Long refreshExpireHour;

 private final SecretKey secretKey;

 public JwtUtil(@Value("${spring.jwt.secret}")
                 String secret
 ) {
  secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
 }

 private Claims getPayload(String token) {
  return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
 }

 public String getUsername(String token) {
  return getPayload(token).get("username", String.class);
 }

 public String createAccessToken(UserDetails userDetails) {
  return generateToken(userDetails,  getAccessExpireMs());
 }

 public String createRefreshToken(String uuid) {
  return generateToken(uuid, getRefreshExpireMs());
 }

 public Long getAccessExpireMs() {
  return accessExpireSecond * 1000;
 }
 public Long getRefreshExpireMs() {
  return refreshExpireHour * 60 * 60 * 1000;
 }

 private String generateToken(UserDetails details, Long expireMs) {
  return Jwts.builder()
      .claim("username", details.getUsername())
      .claim("roles", details.getAuthorities())
      .claim("category", "access")
      .issuedAt(new Date(System.currentTimeMillis()))
      .expiration(new Date(System.currentTimeMillis() + expireMs))
      .signWith(secretKey)
      .compact();
 }

 private String generateToken(String uuid, Long expireMs) {
  return Jwts.builder()
      .claim("uuid", uuid)
      .claim("category", "refresh")
      .issuedAt(new Date(System.currentTimeMillis()))
      .expiration(new Date(System.currentTimeMillis() + expireMs))
      .signWith(secretKey)
      .compact();
 }
}
