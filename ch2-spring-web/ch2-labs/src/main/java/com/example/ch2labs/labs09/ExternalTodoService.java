package com.example.ch2labs.labs09;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalTodoService {
  private final WebClient webClient;

  public ExternalTodoService() {
    this.webClient = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();
  }

  public Post getPost(Long id) {
    return webClient.get().uri("/posts/"+ id).retrieve().bodyToMono(Post.class).block();
  }
}
