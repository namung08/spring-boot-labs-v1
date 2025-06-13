package com.example.ch2labs.labs07.infrastructure.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password,String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if(value == null) {
      return false;
    }
    boolean lengthCheck = value.length() >= 8;
    boolean hasUpperCase = value.matches(".*[A-Z].*");
    boolean hasLowerCase = value.matches(".*[a-z].*");
    boolean hasDigit = value.matches(".*[0-9].*");
    boolean hasSpecial = value.matches(".*[!@#$%^&*()].*");
    return lengthCheck && hasUpperCase && hasLowerCase && hasDigit && hasSpecial;
  }
}
