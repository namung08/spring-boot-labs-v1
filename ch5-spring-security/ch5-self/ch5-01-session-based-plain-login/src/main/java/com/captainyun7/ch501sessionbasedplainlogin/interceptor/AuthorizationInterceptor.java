package com.captainyun7.ch501sessionbasedplainlogin.interceptor;

import com.captainyun7.ch501sessionbasedplainlogin.domain.User;
import com.captainyun7.ch501sessionbasedplainlogin.domain.role.Role;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // TODO : 인터셉터 로직 작성
        String path = request.getRequestURI();
        if(path.startsWith("/api/admin")){
            HttpSession session = request.getSession();
            if(session == null) {
                sendUnauthorizedResponse(response, "Unauthorized");
                return false;
            }
            try {
                User attribute = (User) session.getAttribute(USER_SESSION_KEY);
                if(attribute == null) {
                    sendUnauthorizedResponse(response, "Unauthorized");
                    return false;
                }
                if(!attribute.getRole().equals(Role.ADMIN)) {
                    sendForbiddenResponse(response, "Forbidden need Admin");
                    return false;
                }
            } catch (ClassCastException e) {
                throw new ClassCastException("Current user attribute is not of type User");
            }

            return false;
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
