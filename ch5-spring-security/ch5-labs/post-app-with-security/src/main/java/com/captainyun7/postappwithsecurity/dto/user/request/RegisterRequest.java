package com.captainyun7.postappwithsecurity.dto.user.request;

import com.captainyun7.postappwithsecurity.domain.User;
import com.captainyun7.postappwithsecurity.dto.user.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
 @NotBlank
 private String username;
 @NotBlank
 private String password;

 public void encode(PasswordEncoder encoder) {
  this.password = encoder.encode(password);
 }

 public User to() {
  return User.builder()
      .username(username)
      .password(password)
      .role(Role.USER)
      .build();
 }
}
