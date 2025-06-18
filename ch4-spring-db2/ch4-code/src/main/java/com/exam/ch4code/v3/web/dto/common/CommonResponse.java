package com.exam.ch4code.v3.web.dto.common;

public record CommonResponse<T>(
    Boolean success,
    T data
) {
  public static <T> CommonResponse<T> success(T data) {
    return new CommonResponse<>(true, data);
  }

  public static <T> CommonResponse<T> fail(T data) {
    return new CommonResponse<>(false, data);
  }
}
