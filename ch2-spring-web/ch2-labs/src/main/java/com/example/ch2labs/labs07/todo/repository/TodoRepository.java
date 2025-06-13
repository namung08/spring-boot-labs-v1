package com.example.ch2labs.labs07.todo.repository;

import com.example.ch2labs.labs07.todo.model.Todo;

import java.util.*;

public interface TodoRepository {
  Todo save(Todo todo);

  List<Todo> findAll();

  Optional<Todo> findById(Long id);

  Todo updateTodo(Long id, Todo todo);

  void deleteById(Long id);
}
