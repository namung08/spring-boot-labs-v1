package com.example.ch2labs.labs07.infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TodoException extends RuntimeException {
  private final HttpStatus status;
  private final String errorCode;
  private final String message;


  public TodoException(TodoExceptionCode code) {
    this.status = code.getStatus();
    this.message = code.getMessage();
    this.errorCode = code.getErrorCode();
  }
}
