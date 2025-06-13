package com.example.ch2labs.labs07.web.dto.todo.response;

import com.example.ch2labs.labs07.todo.model.Todo;
import lombok.Builder;

@Builder
public record ResponseTodoDTO(
    Long id,
    String title,
    Boolean completed
) {

  public static ResponseTodoDTO convertor(Todo todo) {
    return new ResponseTodoDTO(todo.getId(), todo.getTitle(), todo.getCompleted());
  }
}
