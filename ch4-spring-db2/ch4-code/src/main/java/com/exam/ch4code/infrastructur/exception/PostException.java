package com.exam.ch4code.infrastructur.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PostException extends RuntimeException {
  private HttpStatus  status;
  private String code;
  private String message;

  public PostException(PostExceptionCode ec) {
    this.status = ec.getStatus();
    this.code = ec.getCode();
    this.message = ec.getMessage();
  }
}
