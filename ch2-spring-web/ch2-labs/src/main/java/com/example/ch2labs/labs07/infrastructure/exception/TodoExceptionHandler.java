package com.example.ch2labs.labs07.infrastructure.exception;

import com.example.ch2labs.labs07.web.dto.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@Slf4j
@ControllerAdvice
public class TodoExceptionHandler {
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
}


