package com.example.ch4labs.todo.service;

import com.example.ch4labs.infrastructure.exception.todo.TodoException;
import com.example.ch4labs.infrastructure.exception.todo.TodoExceptionCode;
import com.example.ch4labs.todo.model.Todo;
import com.example.ch4labs.todo.repository.TodoRepository;
import com.example.ch4labs.web.dto.todo.request.TodoCreateRequest;
import com.example.ch4labs.web.dto.todo.request.TodoSearchRequest;
import com.example.ch4labs.web.dto.todo.request.TodoUpdateRequest;
import com.example.ch4labs.web.dto.todo.response.TodoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
  private final TodoRepository repository;

  @Override
  public TodoResponse createTodo(TodoCreateRequest req) {
    return TodoResponse.from(repository.save(req.toDomain()));
  }

  @Override
  public List<TodoResponse> getTodos(TodoSearchRequest req) {
    List<Todo> todos = repository.findByTitleContainsAllIgnoreCase(req.keyword());
    return todos.stream().map(TodoResponse::from).toList();
  }

  @Override
  @Transactional
  public TodoResponse updateTodo(Long id, TodoUpdateRequest req) {
    Todo todo = repository.findById(id).orElseThrow(() -> new TodoException(TodoExceptionCode.TODO_NOT_FOUND));
    todo.setTitle(req.title());
    todo.setCompleted(req.completed());
    return TodoResponse.from(todo);
  }

  @Override
  public void deleteTodo(Long id) {
    Todo todo = repository.findById(id).orElseThrow(() -> new TodoException(TodoExceptionCode.TODO_NOT_FOUND));
    repository.delete(todo);
  }
}
