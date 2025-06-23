package com.exam.ch503springsecurity.utils;

import com.exam.ch503springsecurity.domain.User;
import com.exam.ch503springsecurity.domain.role.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil {
 private Key key;
 @Value("${spring.config.jwt.ms}")
 private Long expireMs;

 public JwtUtil(@Value("${spring.config.jwt.secret}") String secret) {
  Decoders.BASE64.decode(secret);
  key =  Keys.hmacShaKeyFor(secret.getBytes());
 }

 public String getUsername(String token) {
  return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("username",String.class);
 }

// public String getRole(String token) {
//  return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("role",String.class);
// }

 public Role getRole(String token) {
  return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("role",Role.class);
 }

 public Boolean isExpired(String token) {
  return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
 }

 public Boolean validateToken(String token) {
  return !isExpired(token);
 }

 public String createJwt(User user) {
  Claims claims = Jwts.claims();
  claims.put("username", user.getUsername());
  claims.put("role", user.getRole());
  return Jwts.builder()
      .setClaims(claims)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + expireMs))
      .signWith(key, SignatureAlgorithm.HS256)
      .compact();
 }
}
