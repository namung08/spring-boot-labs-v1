package com.example.ch2labs.labs04;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rps")
public class RpsController {
  @GetMapping
  public Map<String, String> rsp(@RequestParam String user) {
    List<String> choice = List.of("rock", "paper", "scissors");
    int random = (int) (Math.random() * 3);
    String computer = choice.get(random);
    user = user.toLowerCase();

    if (!choice.contains(user)) {
      throw new IllegalArgumentException("Invalid input: user must be rock, paper, or scissors.");
    }

    String result = determineResult(user, computer);

    Map<String, String> map = new LinkedHashMap<>();
    map.put("user", user);
    map.put("computer", computer);
    map.put("result", result);
    return map;
  }

  private String determineResult(String user, String computer) {
    if (user.equals(computer)) return "You Draw!";
    return switch (user) {
      case "rock" -> computer.equals("scissors") ? "You Win!" : "You Lose!";
      case "paper" -> computer.equals("rock") ? "You Win!" : "You Lose!";
      case "scissors" -> computer.equals("paper") ? "You Win!" : "You Lose!";
      default -> throw new IllegalArgumentException("Invalid user input: " + user);
    };
  }
}
