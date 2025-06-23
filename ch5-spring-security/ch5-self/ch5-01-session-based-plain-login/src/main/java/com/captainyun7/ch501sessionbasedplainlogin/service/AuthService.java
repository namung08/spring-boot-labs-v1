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
        // TODO
        return null;
    }

    public UserResponse login(LoginRequest loginRequest, HttpSession session) {
        // TODO
        return null;
    }

    public void logout(HttpSession session) {
        // TODO
    }

    public User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute(USER_SESSION_KEY);
    }
} 