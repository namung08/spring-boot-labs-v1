package com.captainyun7.postappwithsecurity.service;

import com.captainyun7.postappwithsecurity.domain.User;
import com.captainyun7.postappwithsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
 private final UserRepository userRepository;

 @Override
 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  User user = userRepository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException(username));
  return org.springframework.security.core.userdetails.User.builder()
      .username(user.getUsername())
      .password(user.getPassword())
      .authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getSecurityRoleName())))
      .build();
 }
}
