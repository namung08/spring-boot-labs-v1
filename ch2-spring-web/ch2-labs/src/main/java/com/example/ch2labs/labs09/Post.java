package com.example.ch2labs.labs09;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
  private long id;
  private long userId;
  private String title;
  private boolean completed;
}
