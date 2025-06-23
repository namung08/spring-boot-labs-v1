package com.exam.ch502jwtloginbased.filter;

import com.exam.ch502jwtloginbased.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Order(1)
@RequiredArgsConstructor
@Component
public class AuthenticationFilter implements Filter {
    private final JwtUtil jwtUtil;

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
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 모든 사용자가 접근이 가능한 URI 일 경우 다음 필터로 넘어감
        if(isPublicPath(req.getRequestURI())) {
            chain.doFilter(request, response);
        }
        String authorization = req.getHeader("Authorization");

        if(authorization == null || !authorization.startsWith("Bearer ")) {
            unauthorized(res, "need author");
            return;
        }

        // 인증 실패 클라이언트에 반환
        authorization = authorization.substring(7);

        if(jwtUtil.isExpired(authorization)) {
            unauthorized(res, "expired");
        }

        log.info("doFilter");

        chain.doFilter(request, response);
    }

    private boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }

    private void unauthorized(HttpServletResponse res, String message) throws IOException {
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        Map<String, Object> map = new HashMap<>();
        map.put("code", 401);
        map.put("msg", message);
        res.getWriter().write(objectMapper.writeValueAsString(map));
    }
}
