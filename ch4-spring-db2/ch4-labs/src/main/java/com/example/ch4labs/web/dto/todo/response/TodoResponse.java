package com.example.ch4labs.web.dto.todo.response;

import com.example.ch4labs.todo.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponse {
  private Long id;
  private String title;
  private boolean completed;

  public static TodoResponse from(Todo todo) {
    return new TodoResponse(todo.getId(), todo.getTitle(), todo.getCompleted());
  }
}
