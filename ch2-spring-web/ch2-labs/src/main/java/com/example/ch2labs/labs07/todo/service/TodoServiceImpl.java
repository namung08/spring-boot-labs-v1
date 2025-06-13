package com.example.ch2labs.labs07.todo.service;

import com.example.ch2labs.labs07.todo.model.Todo;
import com.example.ch2labs.labs07.todo.repository.TodoRepository;
import com.example.ch2labs.labs07.web.dto.todo.request.CreateTodo;
import com.example.ch2labs.labs07.web.dto.todo.request.UpdateTodo;
import com.example.ch2labs.labs07.web.dto.todo.response.ResponseTodoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
  private final TodoRepository repository;

  @Override
  public ResponseTodoDTO saveTodo(CreateTodo createTodo) {
    Todo todo = new Todo();
    todo.setTitle(createTodo.title());
    todo.setCompleted(createTodo.completed());
    return ResponseTodoDTO.convertor(repository.save(todo));
  }

  @Override
  public List<ResponseTodoDTO> getTodos() {
    return repository.findAll().stream().map(ResponseTodoDTO::convertor).toList();
  }

  @Override
  public ResponseTodoDTO updateTodo(Long id, UpdateTodo updateTodo) {
    Todo todo = new Todo(null, updateTodo.title(), updateTodo.completed());
    return ResponseTodoDTO.convertor(repository.updateTodo(id, todo));
  }

  @Override
  public void deleteTodo(Long id){
    repository.deleteById(id);
  }
}
