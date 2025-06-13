package com.example.ch2labs.labs07.web.dto.todo.request;

public record UpdateTodo(
    String title,
    Boolean completed
) {
}
