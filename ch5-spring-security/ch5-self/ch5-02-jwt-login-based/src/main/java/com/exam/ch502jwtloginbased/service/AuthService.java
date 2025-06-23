package com.exam.ch502jwtloginbased.service;

import com.exam.ch502jwtloginbased.dto.LoginRequest;
import com.exam.ch502jwtloginbased.dto.LoginResponse;
import com.exam.ch502jwtloginbased.dto.SignUpRequest;
import com.exam.ch502jwtloginbased.dto.UserResponse;
import com.exam.ch502jwtloginbased.domain.User;
import com.exam.ch502jwtloginbased.utils.JwtUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;

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

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userService.getUserByUsername(loginRequest.getUsername())
                               .orElseThrow(() -> new RuntimeException("username or password not match"));
        if(!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("username or password not match");
        }

        String token = jwtUtil.createJwt(user);



        return LoginResponse.builder()
            .userId(user.getId())
            .email(user.getEmail())
            .role(user.getRole())
            .token(token)
            .build();
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
