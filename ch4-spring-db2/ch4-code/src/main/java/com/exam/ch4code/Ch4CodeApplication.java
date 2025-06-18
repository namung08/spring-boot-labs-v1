package com.exam.ch4code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.exam.ch4code.v3.post.repository")
@EntityScan(basePackages = "com.exam.ch4code.v3.post.model")
public class Ch4CodeApplication {

  public static void main(String[] args) {
    SpringApplication.run(Ch4CodeApplication.class, args);
  }

}
