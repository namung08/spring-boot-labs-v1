package com.example.ch2labs.labs05;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("sum-digits")
public class SumController {
  @GetMapping
  public ResponseEntity<Map<String, String>> getSumDigits(@RequestParam String number) {
    if(number ==null) {
      return ResponseEntity.badRequest().body(Map.of("error", "number 파라미터는 필수 입니다."));
    }
    int numberInt;
    try {
      numberInt = Integer.parseInt(number);
    }catch (NumberFormatException e) {
      return ResponseEntity.unprocessableEntity().body(Map.of("error", "정수만 입력 가능합니다. 예: /sum-digits?number=1234"));
    }
    if(numberInt < 0) {
      return ResponseEntity.badRequest().body(Map.of("error", "음수는 지원하지 않습니다. 양의 정수를 입력해주세요."));
    }
    int sum = Arrays.stream(number.split(""))
        .mapToInt(Integer::parseInt)
        .sum();


    Map<String, String> map = new HashMap<>();
    map.put("message", "각 자리수 합: " + sum);
    return ResponseEntity.ok(map);
  }
}
