package com.example.ch4labs.web.dto;

public interface Response<T, R extends Response<T, R>> {
  R from(T domain);
}
