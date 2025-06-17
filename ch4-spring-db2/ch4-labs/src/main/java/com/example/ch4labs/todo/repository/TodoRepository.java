package com.example.ch4labs.todo.repository;

import com.example.ch4labs.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
  List<Todo> findByTitleContainsAllIgnoreCase(String title);
}
