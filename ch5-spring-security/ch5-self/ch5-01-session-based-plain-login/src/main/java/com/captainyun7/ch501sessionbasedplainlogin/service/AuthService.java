package com.captainyun7.ch501sessionbasedplainlogin.service;

import com.captainyun7.ch501sessionbasedplainlogin.dto.LoginRequest;
import com.captainyun7.ch501sessionbasedplainlogin.dto.SignUpRequest;
import com.captainyun7.ch501sessionbasedplainlogin.dto.UserResponse;
import com.captainyun7.ch501sessionbasedplainlogin.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private static final String USER_SESSION_KEY = "CURRENT_USER";

    public UserResponse register(SignUpRequest signUpRequest) {
        if(userService.existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if(userService.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        return userService.createUser(signUpRequest);
    }

    public UserResponse login(LoginRequest loginRequest, HttpSession session) {
        User user = userService.getUserByUsername(loginRequest.getUsername())
                               .orElseThrow(() -> new RuntimeException("username or password not match"));
        if(!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("username or password not match");
        }

        session.setAttribute(USER_SESSION_KEY, user);

        return UserResponse.fromEntity(user);
    }

    public void logout(HttpSession session) {
        // 세션에서 사용자 정보 지우기
        session.removeAttribute(USER_SESSION_KEY);
        // 세션 무효화 시키기
        session.invalidate();
    }

    public User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute(USER_SESSION_KEY);
    }
}
