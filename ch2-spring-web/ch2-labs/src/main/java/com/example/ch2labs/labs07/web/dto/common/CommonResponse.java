package com.example.ch2labs.labs07.web.dto.common;

public record CommonResponse<T>(
    Boolean success,
    T data
) {

  public static <T> CommonResponse<T> sussess(T data) {
    return new CommonResponse<>(true, data);
  }

  public static <T> CommonResponse<T> fail(T data) {
    return new CommonResponse<>(false, data);
  }
}
