package com.example.ch2labs.labs07.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TodoExceptionCode {
  TODO_NOT_FOUND(
      HttpStatus.NOT_FOUND,
      "TODO_000",
      "No to-do information found."
  ), TODO_VALIDATION(
      HttpStatus.BAD_REQUEST,
      "TODO_001",
      "Missing content for title or completed.");

  private final HttpStatus status;
  private final String errorCode;
  private final String message;
}
