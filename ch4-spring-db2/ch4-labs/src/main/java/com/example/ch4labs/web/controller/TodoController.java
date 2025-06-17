package com.example.ch4labs.web.controller;

import com.example.ch4labs.todo.service.TodoService;
import com.example.ch4labs.web.dto.todo.request.TodoCreateRequest;
import com.example.ch4labs.web.dto.todo.request.TodoRequest;
import com.example.ch4labs.web.dto.todo.request.TodoSearchRequest;
import com.example.ch4labs.web.dto.todo.request.TodoUpdateRequest;
import com.example.ch4labs.web.dto.todo.response.TodoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {
  private final TodoService service;

  @PostMapping
  public ResponseEntity<TodoResponse> createTodo(@RequestBody TodoCreateRequest req) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.createTodo(req));
  }

  @PutMapping("/{id}")
  public ResponseEntity<TodoResponse> updateTodo(@PathVariable Long id, @RequestBody TodoUpdateRequest req) {
    return ResponseEntity.status(HttpStatus.OK).body(service.updateTodo(id, req));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<TodoResponse> deleteTodo(@PathVariable Long id) {
    service.deleteTodo(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<List<TodoResponse>> getTodos(TodoSearchRequest req) {
    return ResponseEntity.status(HttpStatus.OK).body(service.getTodos(req));
  }
}
