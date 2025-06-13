package com.example.ch2labs.labs07.todo.repository;

import com.example.ch2labs.labs07.infrastructure.exception.TodoException;
import com.example.ch2labs.labs07.infrastructure.exception.TodoExceptionCode;
import com.example.ch2labs.labs07.todo.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TodoRepositoryImpl implements TodoRepository {
  private final Map<Long, Todo> store = new LinkedHashMap<>();
  private Long seq = 0L;
  @Override
  public Todo save(Todo todo) {
    seq++;
    todo.setId(seq);
    store.put(seq, todo);
    return store.get(seq);
  }

  @Override
  public List<Todo> findAll() {
    return new ArrayList<>(store.values());
  }

  @Override
  public Optional<Todo> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Todo updateTodo(Long id, Todo todo) {
    Todo findTodo = findById(id).orElseThrow(() -> new TodoException(TodoExceptionCode.TODO_NOT_FOUND));
    findTodo.setTitle(todo.getTitle());
    findTodo.setCompleted(todo.getCompleted());
    return store.replace(id, findTodo);
  }

  @Override
  public void deleteById(Long id) {
    findById(id).orElseThrow(() -> new TodoException(TodoExceptionCode.TODO_NOT_FOUND));
    store.remove(id);
  }
}
