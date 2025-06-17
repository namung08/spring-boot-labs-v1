package com.exam.ch4code.v1.infrastructur.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PostExceptionCode {
  INTERNAL_SERVER_ERROR(
      HttpStatus.INTERNAL_SERVER_ERROR,
      "POST_000",
      "unknown error"
  ), POST_NOT_FOUND(
      HttpStatus.NOT_FOUND,
      "POST_001",
      "post not found"
  ), UPDATE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "POST_002", "update failed");

  private HttpStatus status;
  private String code;
  private String message;

}
