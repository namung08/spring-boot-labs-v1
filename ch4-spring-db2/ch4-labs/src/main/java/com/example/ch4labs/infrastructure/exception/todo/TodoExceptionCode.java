package com.example.ch4labs.infrastructure.exception.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TodoExceptionCode {
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
                        "TODO_000",
                        "server error"),
  TODO_NOT_FOUND(HttpStatus.NOT_FOUND,
                   "TODO_001",
                   "No todo information found" );

  private final HttpStatus status;
  private final String code;
  private final String message;
}
