package com.captainyun7.ch503sessionbasedspringsecuritylogin.config;


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


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            // TODO: [1] URL별 접근 권한을 설정합니다.
//            .authorizeHttpRequests((

            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())
            // TODO: [2] 세션 관리 설정을 합니다.
            // 세션 관리 설정
            .sessionManagement(session ->
                // 세션 생성 정책을 설정: 필요할 때만 세션 생성
                session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    // 하나의 계정에 대해 최대 세션 수를 1개로 제한
                    .maximumSessions(1)
                    // 세션이 만료되었을 때 리다이렉트할 URL 지정
                    .expiredUrl("/api/auth/login")
                    // 최대 세션 수 초과 시 기존 세션을 만료시키고 새로운 로그인 허용
                    .maxSessionsPreventsLogin(false)
            )
            // SecurityContext 설정
            .securityContext(securityContext ->
                // SecurityContext를 자동으로 저장하도록 설정 (true면 명시적 저장 필요)
                securityContext.requireExplicitSave(false)
            )
        ;

        // H2 콘솔 사용을 위한 설정
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }

    // TODO: [1] PasswordEncoder를 Bean으로 등록합니다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
