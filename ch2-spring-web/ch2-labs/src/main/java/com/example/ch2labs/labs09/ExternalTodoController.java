package com.example.ch2labs.labs09;

import com.example.ch2labs.labs07.web.dto.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external-todos")
@RequiredArgsConstructor
public class ExternalTodoController {
  private final ExternalTodoService service;

  @GetMapping("/{id}")
  public ResponseEntity<CommonResponse<Post>> getPost(@PathVariable long id) {
    return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.sussess(service.getPost(id)));
  }
}
