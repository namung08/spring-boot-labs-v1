package com.example.ch4labs.web.dto.todo.request;

import lombok.Builder;

@Builder
public record TodoSearchRequest(
    String keyword
) {
}
