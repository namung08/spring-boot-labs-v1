package com.exam.ch502jwtloginbased.interceptor;

import com.exam.ch502jwtloginbased.domain.User;
import com.exam.ch502jwtloginbased.domain.role.Role;
import com.exam.ch502jwtloginbased.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {

    private static final String USER_SESSION_KEY = "CURRENT_USER";
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // TODO : 인터셉터 로직 작성
        String path = request.getRequestURI();
        if(path.startsWith("/api/admin")){
            String token = request.getHeader("Authorization");
            if(token != null && token.startsWith("Bearer ")) {
                token =  token.substring(7);
            }

            if(token == null) {
                sendUnauthorizedResponse(response, "need token");
                return false;
            }

            if(!jwtUtil.isExpired(token)) {
                sendUnauthorizedResponse(response, "expired");
                return false;
            }

            Role role = jwtUtil.getRole(token);
            if(!role.equals(Role.ADMIN)){
                sendForbiddenResponse(response, "forbidden");
                return false;
            }
        }

        return true;
    }

    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws Exception {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", message);
        errorResponse.put("status", "error");

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

    private void sendForbiddenResponse(HttpServletResponse response, String message) throws Exception {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", message);
        errorResponse.put("status", "error");

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
