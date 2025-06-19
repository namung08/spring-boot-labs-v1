package com.example.ch4labs.infrastructure.utils.dsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import java.util.function.Supplier;

public class QueryDSLUtils {

  public static void addConditionByString(BooleanBuilder builder, String text, BooleanExpression expression ) {
    if (StringUtils.hasText(text)) {
      builder.and(expression);
    }
  }

  public static void addConditionByInteger(BooleanBuilder builder, Integer value, BooleanExpression expression ) {
    if(value != null) {
      builder.and(expression);
    }
  }

  public static String toLikePattern(String keyword, LikePattern pattern) {
    String patternString;
    switch (pattern) {
      case LEFT ->  patternString = "%" + keyword;
      case RIGHT ->  patternString = keyword + "%";
      case BOTH ->  patternString = "%" + keyword + "%";
      default -> patternString = keyword;
    }
    return patternString;
  }
}

