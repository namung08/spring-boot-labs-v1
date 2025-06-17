package com.exam.ch4code.v2.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PostException extends RuntimeException {
  private final HttpStatus  status;
  private final String code;
  private final String message;

  public PostException(PostExceptionCode ec) {
    this.status = ec.getStatus();
    this.code = ec.getCode();
    this.message = ec.getMessage();
  }
}
