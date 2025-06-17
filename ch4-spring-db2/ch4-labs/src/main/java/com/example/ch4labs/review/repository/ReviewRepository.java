package com.example.ch4labs.review.repository;

import com.example.ch4labs.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
