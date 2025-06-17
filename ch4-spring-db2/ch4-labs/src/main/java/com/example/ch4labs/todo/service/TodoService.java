package com.example.ch4labs.todo.service;

import com.example.ch4labs.web.dto.todo.request.TodoCreateRequest;
import com.example.ch4labs.web.dto.todo.request.TodoSearchRequest;
import com.example.ch4labs.web.dto.todo.request.TodoUpdateRequest;
import com.example.ch4labs.web.dto.todo.response.TodoResponse;

import java.util.List;

public interface TodoService {
  TodoResponse createTodo(TodoCreateRequest req);
  List<TodoResponse> getTodos(TodoSearchRequest req);
  TodoResponse updateTodo(Long id, TodoUpdateRequest req);
  void deleteTodo(Long id);
}
