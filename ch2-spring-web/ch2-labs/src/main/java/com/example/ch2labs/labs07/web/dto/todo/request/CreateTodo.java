package com.example.ch2labs.labs07.web.dto.todo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTodo(
    @NotNull(message = "Missing content for title or completed.")
    @NotBlank(message = "Missing content for title or completed.")
    String title,
    @NotNull(message = "Missing content for title or completed.")
    Boolean completed) {
}
