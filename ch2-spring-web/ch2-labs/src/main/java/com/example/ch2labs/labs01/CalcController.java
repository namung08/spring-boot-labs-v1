package com.example.ch2labs.labs01;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calc")
public class CalcController {
  @GetMapping
  public String calc(@RequestParam("x") int x, @RequestParam("y") int y, @RequestParam("op") String op){
    StringBuilder builder = new StringBuilder();
    switch (op) {
      case "add":
        builder.append(x).append("+").append(y).append(" = ").append(x+y);
        break;
      case "sub":
        builder.append(x).append("-").append(y).append(" = ").append(x-y);
        break;
      case "mul":
        builder.append(x).append("*").append(y).append(" = ").append(x*y);
        break;
      case "div":
        builder.append(x).append("/").append(y).append(" = ").append(x/y);
        break;
      default:
        break;
    }
    return builder.toString();
  }
}
