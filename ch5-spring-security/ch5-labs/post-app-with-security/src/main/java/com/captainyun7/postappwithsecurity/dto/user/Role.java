package com.captainyun7.postappwithsecurity.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
 ADMIN("ROLE_ADMIN"), USER("ROLE_USER");
 private final String securityRoleName;
}
