package com.exam.ch502jwtloginbased.config;

import com.exam.ch502jwtloginbased.interceptor.AuthorizationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    // TODO
 private final AuthorizationInterceptor authorizationInterceptor;
 @Override
 public void addInterceptors(InterceptorRegistry registry) {
  registry.addInterceptor(authorizationInterceptor)
      .addPathPatterns("/api/**")
      .excludePathPatterns("/api/auth/**");
 }
}
