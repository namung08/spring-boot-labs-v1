package com.example.ch4labs.review.specification;

import com.example.ch4labs.review.model.Review;
import org.springframework.data.jpa.domain.Specification;

public class ReviewSpecification {
  public static Specification<Review> byBookTitleContains(String bookTitle) {
    return (root, query, cb) -> cb.like(root.get("bookTitle"),"%"+bookTitle+"%");
  }

  public static Specification<Review> byAuthor(String author) {
    return (root, query, cb) -> cb.equal(root.get("author"),author);
  }

  public static Specification<Review> byRating(Integer rating) {
    return (root, query, cb) -> cb.equal(root.get("rating"),rating);
  }

  public static Specification<Review> byGreaterMinRating(Integer minRating) {
    return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("rating"),minRating);
  }

  public static Specification<Review> byLessMaxRating(Integer maxRating) {
    return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("rating"),maxRating);
  }
}
