package com.example.ch2labs.labs07.todo.model;

import com.example.ch2labs.labs07.todo.repository.TodoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TodoInit {
  private final TodoRepository repository;

  @PostConstruct
  public void init() {
    repository.save(new Todo(null,"할일 1", true));
    repository.save(new Todo(null,"할일 2", false));
    repository.save(new Todo(null,"할일 3", true));
    repository.save(new Todo(null,"할일 4", false));
    repository.save(new Todo(null,"할일 5", true));
  }
}
