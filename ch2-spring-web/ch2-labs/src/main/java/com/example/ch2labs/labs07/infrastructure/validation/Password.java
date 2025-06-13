package com.example.ch2labs.labs07.infrastructure.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
  String message() default "Password must be at least 8 characters long and contain numbers, capital letters, and special characters.";// 속성
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
