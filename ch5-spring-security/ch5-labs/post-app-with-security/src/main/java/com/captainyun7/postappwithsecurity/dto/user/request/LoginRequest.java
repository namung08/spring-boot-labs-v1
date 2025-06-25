package com.captainyun7.postappwithsecurity.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
 @NotBlank
 private String username;
 @NotBlank
 private String password;
}
