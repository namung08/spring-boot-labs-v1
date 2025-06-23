package com.captainyun7.ch501sessionbasedplainlogin.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Order(1)
@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {
    
    private static final String USER_SESSION_KEY = "CURRENT_USER";
    private static final List<String> PUBLIC_PATHS = Arrays.asList(
            "/swagger-ui",
            "/v3/api-docs",
            "/swagger-resources/**",
            "/api/auth/login",
            "/api/auth/register",
            "/h2-console"
    );
    
    private final ObjectMapper objectMapper;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO : 필터 로직 작성
    }
    
    private boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }
} 