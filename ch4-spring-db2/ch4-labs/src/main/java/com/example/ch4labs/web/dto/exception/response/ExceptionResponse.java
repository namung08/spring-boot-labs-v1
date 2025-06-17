package com.example.ch4labs.web.dto.exception.response;

import lombok.Builder;

@Builder
public record ExceptionResponse(
    String code,
    String message
) {
  public static ExceptionResponse toResponse(String code, String message) {
    return new ExceptionResponse(code, message);
  }
}
