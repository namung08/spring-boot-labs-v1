package com.example.ch2labs.labs03;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/random")
public class RandomController {
  @GetMapping
  public Map<String, Integer> random(@RequestParam("min")int min,@RequestParam("max")int max) {
    int random = (int)(Math.random()*(max-min+1)+min);
    Map<String, Integer> data = new LinkedHashMap<>();
    data.put("number", random);
    return data;
  }
}
