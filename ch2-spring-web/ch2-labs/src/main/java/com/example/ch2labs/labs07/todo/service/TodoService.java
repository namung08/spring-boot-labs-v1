package com.example.ch2labs.labs07.todo.service;

import com.example.ch2labs.labs07.web.dto.todo.request.CreateTodo;
import com.example.ch2labs.labs07.web.dto.todo.request.UpdateTodo;
import com.example.ch2labs.labs07.web.dto.todo.response.ResponseTodoDTO;

import java.util.List;

public interface TodoService {
  ResponseTodoDTO saveTodo(CreateTodo createTodo);
  List<ResponseTodoDTO> getTodos();
  ResponseTodoDTO updateTodo(Long id, UpdateTodo  updateTodo);
  void deleteTodo(Long id);
}
