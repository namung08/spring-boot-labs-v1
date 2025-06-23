package com.exam.ch503springsecurity.dto;

import com.exam.ch503springsecurity.domain.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
 private Long userId;
 private String email;
 private Role role;
 private String token;
}
