package com.example.ch2labs.labs07.web.controller;

import com.example.ch2labs.labs07.infrastructure.exception.TodoException;
import com.example.ch2labs.labs07.infrastructure.exception.TodoExceptionCode;
import com.example.ch2labs.labs07.todo.model.Todo;
import com.example.ch2labs.labs07.todo.service.TodoService;
import com.example.ch2labs.labs07.web.dto.common.CommonResponse;
import com.example.ch2labs.labs07.web.dto.todo.request.CreateTodo;
import com.example.ch2labs.labs07.web.dto.todo.request.UpdateTodo;
import com.example.ch2labs.labs07.web.dto.todo.response.ResponseTodoDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {
  private final TodoService service;

  @PostMapping
  public ResponseEntity<CommonResponse<ResponseTodoDTO>> createTodo(@RequestBody @Valid CreateTodo todo
//      , BindingResult result
  ) {
    // 바인딩 리절트의 경우 TypeScript 를 사용할 때 좋다
    // 하지만 우리는 하지 않는다.
//    if(result.hasErrors()) {
//
//    }
    return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.sussess(service.saveTodo(todo)));
  }

  @GetMapping
  public ResponseEntity<CommonResponse<List<ResponseTodoDTO>>> getAllTodos() {
    return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.sussess(service.getTodos()));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CommonResponse<ResponseTodoDTO>> updateTodo(@RequestBody UpdateTodo todo, @PathVariable Long id) {
    if(todo == null || todo.title() == null || todo.completed() == null || todo.title().isBlank()) {
      throw new TodoException(TodoExceptionCode.TODO_VALIDATION);
    }
    return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.sussess(service.updateTodo(id, todo)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CommonResponse<ResponseTodoDTO>> deleteTodo(@PathVariable Long id) {
    service.deleteTodo(id);
    return ResponseEntity.noContent().build();
  }
}
