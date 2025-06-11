package com.example.ch2labs.labs02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dice")
public class DiceController {
  @GetMapping
  public Map<String, Integer> getDice() {
    Map<String, Integer> map = new HashMap<>();
    int random = (int) (Math.random()*6 + 1);
    map.put("dice",random);
    return map;
  }
}
