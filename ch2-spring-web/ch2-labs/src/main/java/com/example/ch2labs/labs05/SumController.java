package com.example.ch2labs.labs05;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("sum-digits")
public class SumController {
  @GetMapping
  public ResponseEntity<Map<String, String>> getSumDigits(@RequestParam Integer number) {
    int sum = 0;

    while (number> 0) {
      int digit = number % 10;
      sum += digit;
      number = number / 10;
    }

    Map<String, String> map = new HashMap<>();
    map.put("message", "각 자리수 합: " + sum);
    return ResponseEntity.ok(map);
  }
}
