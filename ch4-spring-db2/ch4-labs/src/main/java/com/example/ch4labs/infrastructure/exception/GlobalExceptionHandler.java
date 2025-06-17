package com.example.ch4labs.infrastructure.exception;

import com.example.ch4labs.web.dto.exception.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ReviewException.class)
  public ResponseEntity<ExceptionResponse> handleException(ReviewException ex) {
    log.error(ex.getMessage(), ex);
    return ResponseEntity.status(ex.getStatus()).body(ExceptionResponse.toResponse(ex.getCode(), ex.getMessage()));
  }
}
