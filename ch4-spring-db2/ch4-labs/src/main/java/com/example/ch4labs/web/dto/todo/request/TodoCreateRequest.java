package com.example.ch4labs.web.dto.todo.request;

import com.example.ch4labs.todo.model.Todo;
import lombok.Builder;

@Builder
public record TodoCreateRequest(
    String title,
    Boolean completed
) implements TodoRequest{
  @Override
  public Todo toDomain() {
    return Todo.builder()
        .title(title)
        .completed(completed)
        .build();
  }
}
