package com.captainyun7.postappwithsecurity.dto.user.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
 private String accessToken;
}
