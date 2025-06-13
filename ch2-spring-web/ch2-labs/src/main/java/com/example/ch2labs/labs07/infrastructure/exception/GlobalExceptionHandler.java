package com.example.ch2labs.labs07.infrastructure.exception;

import com.example.ch2labs.labs07.web.dto.common.CommonResponse;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
  private Map<String, String> generateMessage(HttpStatus status,String code, String message) {
    return Map.of("status",status.toString(),"error_code", code, "message", message);
  }

  @ExceptionHandler(TodoException.class)
  public ResponseEntity<CommonResponse<Map<String, String >>> handleTodoException(TodoException c) {
    log.error(c.getErrorCode());
    log.error(c.getMessage());
    return ResponseEntity.status(c.getStatus())
                         .body(CommonResponse.fail(generateMessage(c.getStatus(),c.getErrorCode(), c.getMessage())));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<CommonResponse<Map<String, String >>> handleValidationException(MethodArgumentNotValidException c) {
    log.error(c.getMessage());
    Map<String, String> errors = new LinkedHashMap<>();
    c.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(CommonResponse.fail(errors));
  }
}


