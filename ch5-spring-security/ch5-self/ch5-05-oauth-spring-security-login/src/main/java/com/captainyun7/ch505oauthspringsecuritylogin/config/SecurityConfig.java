package com.captainyun7.ch505oauthspringsecuritylogin.config;

import com.captainyun7.ch505oauthspringsecuritylogin.filter.JwtAuthenticationFilter;
import com.captainyun7.ch505oauthspringsecuritylogin.handler.OAuth2AuthenticationFailureHandler;
import com.captainyun7.ch505oauthspringsecuritylogin.handler.OAuth2AuthenticationSuccessHandler;
import com.captainyun7.ch505oauthspringsecuritylogin.service.OAuth2UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OAuth2UserServiceImpl oAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/api/auth/**", "/h2-console/**", "/oauth2/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            // TODO : OIDC 설정
            .oauth2Login(oauth ->
                // 사용자 정보를 가져오는 방식에 대한 설정입니다
                oauth.userInfoEndpoint(userInfoEndpointConfig ->
                        // OpenID Connect 기반 사용자 정보 서비스 지정
                        // 로그인한 사용자의 idToken, userInfo 등을 통해 사용자 정보를 로딩하는 서비스
                        userInfoEndpointConfig.oidcUserService(oAuth2UserService)
                    )
                    // 로그인 성공 시 실행할 핸들러 지정
                    // 예: JWT 토큰 발급, 리다이렉션 처리, 추가 사용자 등록 처리 등
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    // 로그인 실패 시 실행할 핸들러 지정
                    // 예: 에러 로그 기록, 실패 응답 전송, 로그인 페이지로 리다이렉트 등
                    .failureHandler(oAuth2AuthenticationFailureHandler)
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        // H2 콘솔 사용을 위한 설정
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
