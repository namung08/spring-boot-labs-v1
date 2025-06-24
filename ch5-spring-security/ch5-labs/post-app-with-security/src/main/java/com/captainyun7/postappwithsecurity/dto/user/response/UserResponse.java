package com.captainyun7.postappwithsecurity.dto.user.response;

import com.captainyun7.postappwithsecurity.domain.User;
import com.captainyun7.postappwithsecurity.dto.user.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
 private String username;
 private Role role;

 public static UserResponse of(User user){
  return  UserResponse.builder().username(user.getUsername()).role(user.getRole()).build();
 }
}
