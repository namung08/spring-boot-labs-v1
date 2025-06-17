package com.example.ch4labs.infrastructure.exception.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ReviewException extends RuntimeException{
  private final HttpStatus status;
  private final String code;
  private final String message;

  public ReviewException(ReviewExceptionCode rec) {
    this(rec.getStatus(), rec.getCode(), rec.getMessage());
  }
}
