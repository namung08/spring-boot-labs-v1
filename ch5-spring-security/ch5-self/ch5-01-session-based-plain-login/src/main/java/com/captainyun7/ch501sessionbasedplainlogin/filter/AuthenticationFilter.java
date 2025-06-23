package com.captainyun7.ch501sessionbasedplainlogin.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
        HttpSession session = req.getSession(false);

        // 세션이 없거나 USER_SESSION_KEY를 통해 가져온 정보가 null일 경우
        if(session == null || session.getAttribute(USER_SESSION_KEY) == null) {
            // 인증 실패 클라이언트에 반환
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            Map<String, String> errorRes = Map.of("error", "need auth", "status", "error");
            res.getWriter().write(objectMapper.writeValueAsString(errorRes));
            return;
        }

        log.info("doFilter");

        chain.doFilter(request, response);
    }

    private boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }
}
