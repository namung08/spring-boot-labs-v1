package com.exam.ch4code.v2.exception;

import com.exam.ch4code.v2.web.dto.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RestControllerAdvice
@Profile("v2")
public class GlobalExceptionHandler {
  @ExceptionHandler(PostException.class)
  public ResponseEntity<CommonResponse<Map<String, String>>> handlePostException(PostException ex) {
    return ResponseEntity.status(ex.getStatus()).body(
        CommonResponse.fail(Map.of("code", ex.getCode(), "message", ex.getMessage()))
    );
  }
}
