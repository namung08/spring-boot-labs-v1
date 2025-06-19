package com.example.ch4labs.web.dto;

public interface Request<T> {
  T toDomain();
}
