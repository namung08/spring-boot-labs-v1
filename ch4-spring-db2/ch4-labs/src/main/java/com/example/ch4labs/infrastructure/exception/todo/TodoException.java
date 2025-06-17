package com.example.ch4labs.infrastructure.exception.todo;

import com.example.ch4labs.infrastructure.exception.review.ReviewExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class TodoException extends RuntimeException{
  private final HttpStatus status;
  private final String code;
  private final String message;

  public TodoException(TodoExceptionCode rec) {
    this(rec.getStatus(), rec.getCode(), rec.getMessage());
  }
}
