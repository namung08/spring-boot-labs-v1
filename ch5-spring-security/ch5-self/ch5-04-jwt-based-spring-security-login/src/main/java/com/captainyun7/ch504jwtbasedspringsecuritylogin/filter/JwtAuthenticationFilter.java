package com.captainyun7.ch504jwtbasedspringsecuritylogin.filter;

import com.captainyun7.ch504jwtbasedspringsecuritylogin.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // TODO: JWT 인증 필터 로직을 구현합니다.
        // 1. 요청 헤더(Authorization)에서 'Bearer ' 접두사를 제외한 JWT를 추출합니다.
        String authorization = request.getHeader("Authorization");
        // 2. JWT가 존재하고 유효한 경우, 토큰에서 사용자 이름을 추출합니다.
        if(authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            // spring security 가 원하는 방식임
            // security context holder 에 담아 넘기지 않았으므로 security 가 알아서 처리
            return;
        }
        String accessToken = authorization.substring(7);
        String username = null;
        try {
            username = jwtUtil.getUsernameFromToken(accessToken);
        } catch (ExpiredJwtException e) {

        }
        // 유효한 사용자면 SecurityContext에 이증 정보를 수동으로 넣는 작업
        // 3. SecurityContext에 인증 정보가 없는 경우,
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // UserDetailsService에서 사용자 정보를 로드합니다.
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // 4. 토큰이 유효한지 검증합니다(JwtUtil.validateToken).
            if(jwtUtil.validateToken(accessToken, userDetails)) {
                // 5. 유효한 토큰인 경우, UsernamePasswordAuthenticationToken을 생성하고
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // 사용자의 상세 정보(WebAuthenticationDetailsSource)를 설정합니다.
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // 6. SecurityContextHolder에 인증 정보를 설정합니다.
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", message);
        errorResponse.put("status", "error");

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

    private void sendForbiddenResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", message);
        errorResponse.put("status", "error");

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
