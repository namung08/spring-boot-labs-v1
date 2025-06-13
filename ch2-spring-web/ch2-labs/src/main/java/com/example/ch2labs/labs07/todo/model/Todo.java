package com.example.ch2labs.labs07.todo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Todo {
  private Long id;
  private String title;
  private Boolean completed;
}
