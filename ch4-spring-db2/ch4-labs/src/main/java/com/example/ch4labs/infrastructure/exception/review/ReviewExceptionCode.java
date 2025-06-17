package com.example.ch4labs.infrastructure.exception.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewExceptionCode {
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
                        "REVIEW_000",
                        "server error"),
  REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND,
                   "REVIEW_001",
                   "No review information found" );

  private final HttpStatus status;
  private final String code;
  private final String message;
}
